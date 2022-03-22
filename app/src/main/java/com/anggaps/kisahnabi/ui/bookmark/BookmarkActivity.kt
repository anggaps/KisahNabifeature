package com.anggaps.kisahnabi.ui.bookmark

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.anggaps.kisahnabi.R
import com.anggaps.kisahnabi.databinding.ActivityBookmarkBinding

import com.anggaps.kisahnabi.viewModel.ViewModelFactory
import com.google.android.material.snackbar.Snackbar

class BookmarkActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBookmarkBinding

    private lateinit var viewModel: BookmarkViewModel
    private lateinit var adapter: BookmarkAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBookmarkBinding.inflate(layoutInflater)
        setContentView(binding.root)

        itemTouchHelper.attachToRecyclerView(binding.rvBookmarked)

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

    private val itemTouchHelper = ItemTouchHelper(object : ItemTouchHelper.Callback() {
        override fun getMovementFlags(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder): Int =
            makeMovementFlags(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT)

        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ): Boolean = true

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {

                val swipedPosition = viewHolder.adapterPosition
                val storyEntity = adapter.getSwipedData(swipedPosition)
                storyEntity?.let { viewModel.setBookmark(it) }
            }
    })
    }