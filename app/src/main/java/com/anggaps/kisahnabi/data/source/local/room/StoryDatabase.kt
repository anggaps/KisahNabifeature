package com.anggaps.kisahnabi.data.source.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.anggaps.kisahnabi.data.source.local.entity.StoryEntity


@Database(
    entities = [StoryEntity::class],
    version = 1,
    exportSchema = false
)

abstract class StoryDatabase:RoomDatabase() {
    abstract fun storyDao(): StoryEntity

    companion object{

        @Volatile
        private var INSTANCE:StoryDatabase? = null

        fun getInstance(context: Context):StoryDatabase =
            INSTANCE ?: synchronized(this){
                Room.databaseBuilder(
                    context.applicationContext,
                   StoryDatabase::class.java,
                    "Story.db"
                ).build().apply {
                    INSTANCE = this
                }
            }
    }
}