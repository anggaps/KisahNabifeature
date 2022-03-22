package com.anggaps.kisahnabi.ui.bookmark

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.anggaps.kisahnabi.data.StoryRepository
import com.anggaps.kisahnabi.data.source.local.entity.StoryEntity
import com.anggaps.kisahnabi.vo.Resource

class BookmarkViewModel(private val storyRepository: StoryRepository) : ViewModel() {
    fun getBookmarks() = storyRepository.getBookmarkedStory()

    fun setBookmark(storyEntity: StoryEntity) {
        val newState = !storyEntity.bookmarked
        storyRepository.setStoryBookmark(storyEntity, newState)
    }
}