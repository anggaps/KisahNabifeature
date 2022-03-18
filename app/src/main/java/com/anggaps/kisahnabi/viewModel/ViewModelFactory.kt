package com.anggaps.kisahnabi.viewModel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.anggaps.kisahnabi.data.StoryRepository
import com.anggaps.kisahnabi.di.Injection
import com.anggaps.kisahnabi.ui.detail.DetailViewModel
import com.anggaps.kisahnabi.ui.home.MainViewModel

class ViewModelFactory private constructor(private val mStoryRepository: StoryRepository) : ViewModelProvider
.NewInstanceFactory() {


    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance(context: Context): ViewModelFactory =
            instance ?: synchronized(this) {
                ViewModelFactory(Injection.provideRepository(context)).apply {
                    instance = this
                }
            }
    }

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(MainViewModel::class.java) -> {
                MainViewModel(mStoryRepository) as T
            }
            modelClass.isAssignableFrom(DetailViewModel::class.java) -> {
                DetailViewModel(mStoryRepository) as T
            }
            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }
    }
}