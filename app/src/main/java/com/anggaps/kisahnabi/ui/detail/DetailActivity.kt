package com.anggaps.kisahnabi.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.anggaps.kisahnabi.R
import com.anggaps.kisahnabi.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    private var _activityDetailBinding :ActivityDetailBinding ? = null
    private val binding get() = _activityDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
    }




}