package com.anggaps.kisahnabi.data.source.remote

import android.os.Handler
import android.os.Looper
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.anggaps.kisahnabi.data.source.remote.response.StoryListResponse
import com.anggaps.kisahnabi.utils.JsonHelper

class RemoteDataSource private constructor(private val jsonHelper: JsonHelper) {

    private val handler = Handler(Looper.getMainLooper())

    companion object {
        private const val SERVICE_LATENCY_IN_MILLIS: Long = 1000

        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(helper: JsonHelper): RemoteDataSource =
            instance ?: synchronized(this) {
                RemoteDataSource(helper).apply { instance = this }
            }
    }

    fun getAllStory(): LiveData<ApiResponse<List<StoryListResponse>>> {
//        EspressoIdlingResource.increment()
        val resultCourse = MutableLiveData<ApiResponse<List<StoryListResponse>>>()
        handler.postDelayed({
            resultCourse.value = ApiResponse.success(jsonHelper.loadStory())
//            EspressoIdlingResource.decrement()
        }, SERVICE_LATENCY_IN_MILLIS)
        return resultCourse
    }


    fun getDetailStory(id: String): LiveData<ApiResponse<List<StoryListResponse>>> {
        val resultDetail = MutableLiveData<ApiResponse<List<StoryListResponse>>>()
        handler.postDelayed({
            resultDetail.value = ApiResponse.success(jsonHelper.loadStory())
        }, SERVICE_LATENCY_IN_MILLIS)
        return resultDetail
    }


}