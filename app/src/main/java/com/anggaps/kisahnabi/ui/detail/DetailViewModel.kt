package com.anggaps.kisahnabi.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.anggaps.kisahnabi.data.StoryRepository
import com.anggaps.kisahnabi.data.source.local.entity.StoryEntity
import com.anggaps.kisahnabi.vo.Resource

class DetailViewModel(private val storyRepository: StoryRepository) : ViewModel() {
    val storyId = MutableLiveData<String>()

    private lateinit var detailStory: LiveData<Resource<StoryEntity>>


    fun setSelectedStory(storyId: String) {
        this.storyId.value = storyId
    }


    var story: LiveData<Resource<StoryEntity>> = Transformations.switchMap(storyId) { mStoryId ->
        storyRepository.getDetailStory(mStoryId)
    }

    fun getStoryDetail(): LiveData<Resource<PagedList<StoryEntity>>> =
        storyRepository.getAllStory()

    fun setStory(id: String) {
        detailStory = storyRepository.getDetailStory(id)
    }


    fun setBookmark() {
        val StoryResource = story.value
        if (StoryResource != null) {
            val storyEntity = StoryResource.data

            if (storyEntity != null) {
                val storyEntity = storyEntity
                val newState = !storyEntity.bookmarked
                storyRepository.setStoryBookmark(storyEntity, newState)
            }
        }
    }
}
