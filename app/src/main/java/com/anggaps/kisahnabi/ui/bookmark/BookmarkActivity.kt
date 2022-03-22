package com.anggaps.kisahnabi.ui.bookmark

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.anggaps.kisahnabi.databinding.ActivityBookmarkBinding

import com.anggaps.kisahnabi.viewModel.ViewModelFactory

class BookmarkActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBookmarkBinding

    private lateinit var viewModel: BookmarkViewModel
    private lateinit var adapter: BookmarkAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBookmarkBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val factory = ViewModelFactory.getInstance(this)
        viewModel = ViewModelProvider(this, factory)[BookmarkViewModel::class.java]
        adapter = BookmarkAdapter(this)
        viewModel.getBookmarks().observe(this) { story ->
            if (story.isEmpty()){
                binding.imageView2.visibility = View.VISIBLE
            }else{
                binding.imageView2.visibility = View.GONE
                adapter.submitList(story)

            }


        }

        with(binding) {
            rvBookmarked.layoutManager = LinearLayoutManager(this@BookmarkActivity)
            rvBookmarked.adapter = adapter

        }
    }

}