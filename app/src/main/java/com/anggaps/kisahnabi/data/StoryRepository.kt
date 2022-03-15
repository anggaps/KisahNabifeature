package com.anggaps.kisahnabi.data

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.anggaps.kisahnabi.data.source.local.LocalDataSource
import com.anggaps.kisahnabi.data.source.local.entity.StoryEntity
import com.anggaps.kisahnabi.data.source.remote.ApiResponse
import com.anggaps.kisahnabi.data.source.remote.RemoteDataSource
import com.anggaps.kisahnabi.data.source.remote.response.StoryListResponse
import com.anggaps.kisahnabi.utils.AppExecutors
import com.anggaps.kisahnabi.vo.Resource

class StoryRepository private constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors


) : StoryDataSource {

    companion object {
        @Volatile
        private var instance: StoryRepository? = null

        fun getInstance(
            remoteData: RemoteDataSource,
            localDataSource: LocalDataSource,
            appExecutors: AppExecutors
        ): StoryRepository =
            instance ?: synchronized(this) {
                instance ?: StoryRepository(remoteData, localDataSource, appExecutors).apply { instance = this }
            }
    }

    override fun getAllStory(): LiveData<Resource<PagedList<StoryEntity>>> {
        return object : NetworkBoundResource<PagedList<StoryEntity>, List<StoryListResponse>>(appExecutors) {
            override fun loadFromDB(): LiveData<PagedList<StoryEntity>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(4)
                    .setPageSize(4)
                    .build()
                return LivePagedListBuilder(localDataSource.getAllStory(), config).build()
            }

            override fun shouldFetch(data: PagedList<StoryEntity>?): Boolean =
                data == null || data.isEmpty()


            override fun createCall(): LiveData<ApiResponse<List<StoryListResponse>>> =
                remoteDataSource.getAllStory()

            override fun saveCallResult(data: List<StoryListResponse>) {
                val storyList = ArrayList<StoryEntity>()
                for (response in data) {
                    val story = StoryEntity(
                        response.id,
                        response.titleName,
                        response.desc,
                        false,
                        response.imagePath
                    )
                    storyList.add(story)
                }
                localDataSource.insertStory(storyList)
            }

        }.asLiveData()
    }

    override fun getBookmarkedStory(): LiveData<PagedList<StoryEntity>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(4)
            .setPageSize(4)
            .build()
        return LivePagedListBuilder(localDataSource.getBookmarkedStory(), config).build()
    }

    override fun setStoryBookmark(story: StoryEntity, state: Boolean) =
        appExecutors.diskIO().execute { localDataSource.setStoryBookmark(story, state) }


}