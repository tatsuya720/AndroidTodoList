package com.example.common.model.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class TodoData (
    val id: Long,
    val title: String,
    val description: String
): Parcelable