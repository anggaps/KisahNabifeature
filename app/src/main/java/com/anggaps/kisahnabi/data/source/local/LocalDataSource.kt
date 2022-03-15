package com.anggaps.kisahnabi.data.source.local

import com.anggaps.kisahnabi.data.source.local.entity.StoryEntity
import com.anggaps.kisahnabi.data.source.local.room.StoryDao

class LocalDataSource private constructor(private val mStoryDao: StoryDao) {

    companion object {
        private var INSTANCE: LocalDataSource? = null

        fun getInstance(storyDao: StoryEntity): LocalDataSource =
            INSTANCE ?: LocalDataSource(storyDao)
    }

    fun getAllStory(): androidx.paging.DataSource.Factory<Int, StoryEntity> = mStoryDao.getStory()

    fun getBookmarkedStory(): androidx.paging.DataSource.Factory<Int, StoryEntity> = mStoryDao.getBookmarkedStory()

    fun insertStory(story: List<StoryEntity>) = mStoryDao.insertStory(story)

    fun setStoryBookmark(story: StoryEntity, newState: Boolean) {
        story.bookmarked = newState
        mStoryDao.updateStory(story)
    }
}