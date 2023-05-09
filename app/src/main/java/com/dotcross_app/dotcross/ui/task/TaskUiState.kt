package com.dotcross_app.dotcross.ui.task

import com.dotcross_app.dotcross.data.Selection
import com.dotcross_app.dotcross.data.Task
import java.sql.Date
import java.time.LocalDate

data class TaskUiState (
    val id: Int = 0,
    val name:String = "",
    val datesSelected: Map<LocalDate, Selection> = mutableMapOf<LocalDate, Selection>(),
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

fun TaskUiState.toTaskUiState(enabled: Boolean = false): TaskUiState = TaskUiState(
    id = id,
    name = name,
    datesSelected = datesSelected,
    enabled = enabled
)