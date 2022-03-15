package com.anggaps.kisahnabi.di

import android.content.Context
import com.anggaps.kisahnabi.data.StoryRepository
import com.anggaps.kisahnabi.data.source.local.LocalDataSource
import com.anggaps.kisahnabi.data.source.local.room.StoryDatabase
import com.anggaps.kisahnabi.data.source.remote.RemoteDataSource
import com.anggaps.kisahnabi.utils.AppExecutors
import com.anggaps.kisahnabi.utils.JsonHelper


object Injection {
    fun provideRepository(context: Context): StoryRepository  {

        val database = StoryDatabase.getInstance(context)

        val remoteDataSource = RemoteDataSource.getInstance(JsonHelper(context))
        val localDataSource = LocalDataSource.getInstance(database.storyDao())
        val appExecutors = AppExecutors()

        return StoryRepository.getInstance(remoteDataSource, localDataSource, appExecutors)

    }
}