package com.dotcross_app.dotcross.ui.task

import com.dotcross_app.dotcross.data.Selection
import com.dotcross_app.dotcross.data.Task
import java.util.*

data class TaskUiState (
    val id: Int = 0,
    val name:String = "",
    val datesSelected: Map<Date, Selection> = mapOf<Date, Selection>(),
    val enabled: Boolean = false
)

fun TaskUiState.isValid() : Boolean {
    return name.isNotBlank()
}

fun TaskUiState.toTask(): Task = Task(
    id = id,
    name = name,
    datesSelected = datesSelected
)