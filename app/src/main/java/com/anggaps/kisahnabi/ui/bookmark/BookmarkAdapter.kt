package com.anggaps.kisahnabi.ui.bookmark

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.anggaps.kisahnabi.data.source.local.entity.StoryEntity
import com.anggaps.kisahnabi.databinding.ItemListBinding
import com.anggaps.kisahnabi.ui.detail.DetailActivity

class BookmarkAdapter(private val callback: BookmarkFragmentCallback) :
    PagedListAdapter<StoryEntity, BookmarkAdapter.StoryViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<StoryEntity>() {
            override fun areItemsTheSame(oldItem: StoryEntity, newItem: StoryEntity): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: StoryEntity, newItem: StoryEntity): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoryViewHolder {
        val itemBookmarkBinding = ItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return StoryViewHolder(itemBookmarkBinding)
    }

    override fun onBindViewHolder(
        holder:
        StoryViewHolder, position: Int
    ) {
        val story = getItem(position)
        if (story != null) {
            holder.bind(story)
        }
    }

    class StoryViewHolder(private val binding: ItemListBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(story: StoryEntity) {
            with(binding) {
                tvItemName.text = story.titleName
                tvItemTurun.text = story.tempat
                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailActivity::class.java)
                    intent.putExtra(DetailActivity.EXTRA_STORY, story.id)
                    itemView.context.startActivity(intent)
                }
            }

        }
    }

    }