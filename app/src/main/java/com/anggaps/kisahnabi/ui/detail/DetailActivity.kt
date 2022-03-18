package com.anggaps.kisahnabi.ui.detail

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.anggaps.kisahnabi.data.source.local.entity.StoryEntity
import com.anggaps.kisahnabi.databinding.ActivityDetailBinding
import com.anggaps.kisahnabi.viewModel.ViewModelFactory
import com.anggaps.kisahnabi.vo.Status
import com.anggaps.kisahnabi.vo.Status.*

class DetailActivity : AppCompatActivity() {

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

        val extra = intent.extras
        if (extra != null) {
            val storyId = extra.getString(EXTRA_STORY)
            if (storyId != null) {
                viewModel.setSelectedStory(storyId)

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
            }
        }

    }

    private fun populateStory(storyEntity: StoryEntity) {
        activityDetailBinding.tvNama.text = storyEntity.titleName
        activityDetailBinding.TvUsia.text = storyEntity.usia
        activityDetailBinding.TvTahunKelahiran.text = storyEntity.tahunKelahiran
        activityDetailBinding.WvDetail.loadData(storyEntity.desc, "text/html", "UTF-8")
    }
}