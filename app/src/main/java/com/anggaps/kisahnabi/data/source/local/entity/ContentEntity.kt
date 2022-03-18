package com.anggaps.kisahnabi.data.source.local.entity

import androidx.room.Embedded


data class ContentEntity(
    @Embedded
    var detai: StoryEntity
)
