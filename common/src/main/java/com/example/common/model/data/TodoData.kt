package com.example.common.model.data

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import java.util.*

@Parcelize
@Entity(tableName = "todo_list_table")
data class TodoData (
    @PrimaryKey(autoGenerate = true) val id: Long,
    val title: String,
    val description: String,
    val limitDate: Date
): Parcelable