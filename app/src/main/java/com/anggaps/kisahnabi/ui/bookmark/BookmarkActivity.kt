package com.anggaps.kisahnabi.ui.bookmark

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.anggaps.kisahnabi.R
import com.anggaps.kisahnabi.databinding.ActivityBookmarkBinding

class BookmarkActivity : AppCompatActivity() {

    private var _activityBookmarkBinding: ActivityBookmarkBinding? = null
    private val binding get() = _activityBookmarkBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bookmark)



    }
}