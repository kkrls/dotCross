package com.dotcross_app.dotcross.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "task")
data class Task(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name:String,
    val selected:Int = 0,
    val date: Date? = null
)
