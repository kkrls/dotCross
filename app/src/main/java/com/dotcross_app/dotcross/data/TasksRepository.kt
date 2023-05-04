package com.dotcross_app.dotcross.data
import kotlinx.coroutines.flow.Flow

interface TasksRepository {
    fun getAllTasksStream(): Flow<List<Task>>

    fun getItemStream(id: Int): Flow<Task?>

    suspend fun insertTask(task: Task)

    suspend fun deleteTask(task: Task)

    suspend fun updateTask(task: Task)
}
