package com.anggaps.kisahnabi.ui.detail

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ShareCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.anggaps.kisahnabi.R
import com.anggaps.kisahnabi.data.source.local.entity.StoryEntity
import com.anggaps.kisahnabi.databinding.ActivityDetailBinding
import com.anggaps.kisahnabi.ui.home.MainActivity
import com.anggaps.kisahnabi.viewModel.ViewModelFactory
import com.anggaps.kisahnabi.vo.Status
import com.anggaps.kisahnabi.vo.Status.ERROR
import com.anggaps.kisahnabi.vo.Status.SUCCESS

class DetailActivity() : AppCompatActivity(), View.OnClickListener, DetailActivityCallback {

    private lateinit var activityDetailBinding: ActivityDetailBinding
    private lateinit var viewModel: DetailViewModel

    companion object {
        const val EXTRA_STORY = "extra story"
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityDetailBinding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(activityDetailBinding.root)


        val factory = ViewModelFactory.getInstance(this)
        viewModel = ViewModelProvider(this, factory)[DetailViewModel::class.java]


        activityDetailBinding.btnBookmark.setOnClickListener(this)
        activityDetailBinding.btnBack.setOnClickListener(this)


        val mainAdapter = DetailAdapter()

        val extra = intent.extras
        if (extra != null) {
            val storyId = extra.getString(EXTRA_STORY)
            if (storyId != null) {
                viewModel.setSelectedStory(storyId)
                setupState()
                viewModel.story.observe(this) { storyDetail ->
                    if (storyDetail != null) {
                        when (storyDetail.status) {
                            SUCCESS -> if (storyDetail.data != null) {
                                populateStory(storyDetail.data)
                            }
                            ERROR -> {
                                Toast.makeText(applicationContext, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
                            }
//                            LOADING -> TODO()
                        }
                    }
                }
                viewModel.getStoryDetail().observe(this) { storyDetail ->
                    if (storyDetail != null) {
                        when (storyDetail.status) {
                            SUCCESS -> {
                                mainAdapter.submitList(storyDetail.data)
                            }
                        }
                    }
                }
            }
        }



        with(activityDetailBinding.rvHorizontal) {
            layoutManager = LinearLayoutManager(this@DetailActivity, RecyclerView.HORIZONTAL, false)
            this.adapter = mainAdapter

        }


    }

    private fun setupState(): Boolean {
        viewModel.story.observe(this) { storyid ->
            if (storyid != null) {
                when (storyid.status) {
                    SUCCESS -> if (storyid.data != null) {
                        val state = storyid.data.bookmarked
                        setBookmarkState(state)
                    }
                    Status.LOADING -> {
                        setBookmarkState(false)
                    }
                    Status.ERROR -> {
                        Toast.makeText(applicationContext, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
                    }
                }

            }
        }
        return true
    }

    private fun setBookmarkState(state: Boolean) {
        val fab = activityDetailBinding.btnBookmark
        if (state) {
            fab.setImageResource(R.drawable.ic_bookmark_filled)
        } else {
            fab.setImageResource(R.drawable.ic_bookmark)
        }
    }


    private fun populateStory(storyEntity: StoryEntity) {
        activityDetailBinding.title.text = storyEntity.titleName
        activityDetailBinding.TvIsiUsia.text = storyEntity.usia
        activityDetailBinding.TvIsiTahunKelahiran.text = storyEntity.tahunKelahiran
        activityDetailBinding.wbDetail.loadData(storyEntity.desc, "text/html", "UTF-8")
        activityDetailBinding.buttonBagikan.setOnClickListener {
            onShareClick(storyEntity)
        }
    }


    override fun onClick(p0: View) {
        when (p0.id) {
            R.id.btn_bookmark -> {
                viewModel.setBookmark()
            }
            R.id.btn_back -> {
                val intent = Intent(p0.context, MainActivity::class.java)
               startActivity(intent)
            }
        }
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount <= 1) {
            finish()
        } else {
            super.onBackPressed()
        }
    }

    override fun onShareClick(story: StoryEntity) {
        val nameType = "text/plain"
        ShareCompat.IntentBuilder
            .from(this)
            .setType(nameType)
            .setChooserTitle("Bagikan aplikasi ini sekarang.")
            .setText(
                "Ingin tau kisah nabi ${story.titleName} segera download qara'a sekarang di playstore dan apps " +
                        "store"
            )
            .startChooser()
    }

}