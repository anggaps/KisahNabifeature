package com.anggaps.kisahnabi.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "storyEntities")
data class StoryEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    var id: String,

    @ColumnInfo(name = "titleName")
    var titleName :String,

    @ColumnInfo(name = "turun")
    var turun :String,

    @ColumnInfo(name = "desc")
    var desc :String,

    @ColumnInfo(name = "bookmarked")
    var bookmarked :Boolean,

    @ColumnInfo(name = "imagePath")
    var imagepath :String


)
