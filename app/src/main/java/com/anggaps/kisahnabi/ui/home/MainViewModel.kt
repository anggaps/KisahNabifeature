package com.anggaps.kisahnabi.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.anggaps.kisahnabi.data.StoryRepository
import com.anggaps.kisahnabi.data.source.local.entity.StoryEntity
import com.anggaps.kisahnabi.vo.Resource

class MainViewModel(private val storyRepository: StoryRepository) : ViewModel() {

    fun getStory():LiveData<Resource<PagedList<StoryEntity>>> =
        storyRepository.getAllStory()

}