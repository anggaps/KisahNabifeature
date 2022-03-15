package com.anggaps.kisahnabi.data.source.remote.response

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DetailStoryResponse(
    var content:String
) : Parcelable

