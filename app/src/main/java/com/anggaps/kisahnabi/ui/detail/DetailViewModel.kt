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

//    private lateinit var detailStory: LiveData<Resource<StoryEntity>>

    fun setSelectedStory(storyId: String) {
        this.storyId.value = storyId
    }

    var story: LiveData<Resource<StoryEntity>> = Transformations.switchMap(storyId) { mStoryId ->
        storyRepository.getDetailStory(mStoryId)
    }

    fun getStoryDetail(): LiveData<Resource<PagedList<StoryEntity>>> =
        storyRepository.getAllStory()



    fun setBookmark() {
        val resource = story.value
        if (resource?.data != null) {
            val newState = !resource.data.bookmarked
            storyRepository.setStoryBookmark(resource.data, newState)
        }
    }

    companion object {
        const val STOREYS = "story"
    }
}
