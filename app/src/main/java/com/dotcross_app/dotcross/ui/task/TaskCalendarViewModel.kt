package com.dotcross_app.dotcross.ui.task

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.dotcross_app.dotcross.data.Task
import com.dotcross_app.dotcross.data.TasksRepository

class TaskCalendarViewModel(
    savedStateHandle: SavedStateHandle,
    private val tasksRepository: TasksRepository
) : ViewModel() {

    private val taskId: Int = checkNotNull(savedStateHandle[TaskCalendarDestination.taskId])

    val uiState: Task = tasksRepository.getTask(taskId = taskId)


}