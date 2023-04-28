package com.dotcross_app.dotcross.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity (tableName = "task_entry")
data class TaskEntry(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val taskName: String,
    val selected: Int = 0
)
