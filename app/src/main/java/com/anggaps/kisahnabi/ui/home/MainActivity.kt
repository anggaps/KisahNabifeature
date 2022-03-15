package com.anggaps.kisahnabi.ui.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.anggaps.kisahnabi.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var _activityMainBinding: ActivityMainBinding? = null

    private val binding get() = _activityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _activityMainBinding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding?.root)
    }
}