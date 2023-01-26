package com.example.data

import android.os.Parcelable
import androidx.annotation.Keep
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import java.util.*

@Keep
@Parcelize
@Entity(tableName = "todo_list_table")
data class TodoData (
    @PrimaryKey(autoGenerate = true) val id: Long,
    val title: String,
    val description: String,
    val limitDate: Date,
    val todoState: TodoState,
): Parcelable

enum class TodoState(val value: Int) {
    NotComplete(0),
    Complete(1);

    companion object {
        fun find(value: Int): TodoState? {
            return values().find { it.value == value}
        }
    }
}