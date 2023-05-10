package com.dotcross_app.dotcross.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import java.time.LocalDate
import kotlin.random.Random

class TasksRepository() {

    var tasksList = mutableListOf(
        Task(0, name = "Gym", datesSelected = getRandomDateMap()),
        Task(1, name = "Study Maths", datesSelected = getRandomDateMap()),
        Task(2, name = "Climbing", datesSelected = getRandomDateMap()),
        Task(3, name = "Feed Nemo", datesSelected = getRandomDateMap()),
        Task(4, name = "Journal", datesSelected = getRandomDateMap()),
        Task(5, name = "Water Plants", datesSelected = getRandomDateMap()),
        Task(6, name = "Take Medication", datesSelected = getRandomDateMap())
    )

    private fun getRandomDateMap(): Map<LocalDate, Selection> {
        val mapReturned = mutableMapOf<LocalDate, Selection>()
        val randomNumberOfDays = Random.nextInt(30)
        var i = 1

        while(i < randomNumberOfDays) {
            val date = LocalDate.now().minusDays(i.toLong())
            val selection = Selection.randomValue()
            mapReturned.putIfAbsent(date, selection)
            i++
        }
        return mapReturned
    }

    fun getTaskList(): MutableList<Task> {
        return tasksList
    }

    fun addToTaskList(name: String){
        tasksList.add(Task(id = tasksList.size + 1, name = name))
    }

    fun getTask(taskId: Int): Flow<Task?> {
        return getTask(taskId)
    }

}
