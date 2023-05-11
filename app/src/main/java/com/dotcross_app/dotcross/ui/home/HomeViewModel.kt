package com.dotcross_app.dotcross.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dotcross_app.dotcross.data.Task
import com.dotcross_app.dotcross.data.TasksRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

// View model class to act as the data layer for the Home Screen
class HomeViewModel : ViewModel() {
    // Home screen state
    private val _taskList = MutableStateFlow<List<Task>>(emptyList())

    // Backing property for immutability
    val taskList = _taskList.asStateFlow()

    init {
        fetchTasks()
    }

    private fun fetchTasks() {
        viewModelScope.launch {
            val taskList = TasksRepository().getTaskList()
            _taskList.value = taskList
        }
    }

}
