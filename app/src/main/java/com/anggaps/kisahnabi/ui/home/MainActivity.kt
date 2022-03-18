package com.anggaps.kisahnabi.ui.home

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.anggaps.kisahnabi.databinding.ActivityMainBinding
import com.anggaps.kisahnabi.viewModel.ViewModelFactory
import com.anggaps.kisahnabi.vo.Status

class MainActivity : AppCompatActivity() {


    private lateinit var activityMainBinding: ActivityMainBinding

    private lateinit var viewModel: MainViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        val mainAdapter = MainAdapter()

        val factory = ViewModelFactory.getInstance(this)
        viewModel = ViewModelProvider(this, factory)[MainViewModel::class.java]

        viewModel.getStory().observe(this) { story ->
            if (story != null) {
                when (story.status) {
                    Status.SUCCESS -> {
                        mainAdapter.submitList(story.data)
                    }
                    Status.ERROR -> {
                        Toast.makeText(applicationContext, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
                    }
                }
            }

        }

        with(activityMainBinding.rvStory) {
            layoutManager = LinearLayoutManager(this@MainActivity)

            this.adapter = mainAdapter

        }

    }

}