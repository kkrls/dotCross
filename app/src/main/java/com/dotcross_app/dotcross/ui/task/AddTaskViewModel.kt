package com.dotcross_app.dotcross.ui.task

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.dotcross_app.dotcross.data.TasksRepository

class AddTaskViewModel (private val tasksRepository: TasksRepository): ViewModel() {

    var taskUiState by mutableStateOf(TaskUiState())
        private set

    fun updateUiState(newTaskUiState: TaskUiState){
        taskUiState = newTaskUiState.copy(enabled = newTaskUiState.isValid())
    }

    suspend fun saveTask() {
        if (taskUiState.isValid()) {
            tasksRepository.insertTask(taskUiState.toTask())
        }
    }
}