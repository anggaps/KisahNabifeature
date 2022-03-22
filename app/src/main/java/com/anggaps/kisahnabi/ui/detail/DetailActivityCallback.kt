package com.anggaps.kisahnabi.ui.detail

import com.anggaps.kisahnabi.data.source.local.entity.StoryEntity

interface DetailActivityCallback {
    fun onShareClick(story :StoryEntity)
}