package com.anggaps.kisahnabi.ui.bookmark

import com.anggaps.kisahnabi.data.source.local.entity.StoryEntity

interface BookmarkFragmentCallback {
    fun onShareClick(story: StoryEntity)
}