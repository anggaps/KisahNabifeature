package com.anggaps.kisahnabi.data

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.anggaps.kisahnabi.data.source.local.entity.StoryEntity
import com.anggaps.kisahnabi.vo.Resource

interface StoryDataSource {


    fun getAllStory():LiveData<Resource<PagedList<StoryEntity>>>

    fun getDetailStory(id:String): LiveData<Resource<StoryEntity>>

    fun getBookmarkedStory():LiveData<PagedList<StoryEntity>>

    fun setStoryBookmark(story: StoryEntity, state: Boolean)


}