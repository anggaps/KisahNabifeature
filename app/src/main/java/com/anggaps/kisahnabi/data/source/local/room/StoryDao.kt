package com.anggaps.kisahnabi.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import com.anggaps.kisahnabi.data.source.local.entity.StoryEntity


@Dao
interface StoryDao {

    @Query("SELECT * FROM storyEntities")
    fun getStory(): DataSource.Factory<Int, StoryEntity>


    @Query("SELECT * FROM storyEntities where id =:id ")
    fun getDetailStory(id: String): LiveData<StoryEntity>

    @Query("SELECT * FROM storyEntities where bookmarked = 1")
    fun getBookmarkedStory(): DataSource.Factory<Int, StoryEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertStory(course: List<StoryEntity>)


    @Update
    fun updateStory(course: StoryEntity)

}