package com.anggaps.kisahnabi.data.source.remote.response

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class StoryListResponse(
    var id: String,
    var titleName: String,
    var usia: String,
    var tahunKelahiran: String,
    var tempatKelahiran: String,
    var desc: String,
    var imagePath: String
) : Parcelable
