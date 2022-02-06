package com.akashk.palette.domain.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Palette(
    val id: String,
    val name: String,
    val colorList: MutableList<String>,
    val modifiedAt: Long
) : Parcelable
