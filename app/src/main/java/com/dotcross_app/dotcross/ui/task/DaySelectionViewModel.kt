package com.dotcross_app.dotcross.ui.task

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dotcross_app.dotcross.data.TasksRepository
import com.dotcross_app.dotcross.data.Selection
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.util.Date

class DaySelectionViewModel(taskId: Int, date: LocalDate): ViewModel()  {

    private val _datesSelected = MutableStateFlow<Map<LocalDate, Selection>>(emptyMap())
    val dateSelected = _datesSelected.asStateFlow()

    init {
        fetchSelections(taskId, date)
    }

    private fun fetchSelections(taskId: Int, date: LocalDate) {
        viewModelScope.launch {
            val dateSelected = TasksRepository().getTask(taskId).datesSelected
            _datesSelected.value = dateSelected
        }
    }

}