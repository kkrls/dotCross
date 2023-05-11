package com.dotcross_app.dotcross.ui.task

import com.dotcross_app.dotcross.data.Selection
import java.time.LocalDate

data class TaskUiState(
    val id: Int = 0,
    var name: String = "",
    val datesSelected: Map<LocalDate, Selection> = mutableMapOf<LocalDate, Selection>(),
    val enabled: Boolean = false
)

fun TaskUiState.isValid(): Boolean {
    return name.isNotBlank()
}
