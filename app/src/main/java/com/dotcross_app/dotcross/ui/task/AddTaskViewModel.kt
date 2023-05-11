package com.dotcross_app.dotcross.ui.task

import androidx.lifecycle.ViewModel
import com.dotcross_app.dotcross.data.TasksRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

// ViewModel for AddTask composable
class AddTaskViewModel : ViewModel() {

    private val _name = MutableStateFlow("")

    // Backing property for immutability
    val name: StateFlow<String> = _name.asStateFlow()

    fun setName(name: String) {
        _name.value = name
    }

    fun saveTask() {
        TasksRepository().addToTaskList(name = _name.value)
    }
}