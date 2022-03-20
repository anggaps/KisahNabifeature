package com.anggaps.kisahnabi.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.anggaps.kisahnabi.R
import com.anggaps.kisahnabi.databinding.ActivityMainBinding
import com.anggaps.kisahnabi.ui.bookmark.BookmarkActivity
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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.btnFav -> {
                Intent(this, BookmarkActivity::class.java).also {
                    startActivity(it)
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }


}