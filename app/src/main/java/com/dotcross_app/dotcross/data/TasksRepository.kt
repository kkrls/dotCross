package com.dotcross_app.dotcross.data

import java.time.LocalDate
import java.util.stream.Collectors.toMap
import kotlin.random.Random

// Task repository to be used for app testing before implementing a database functionality
class TasksRepository {

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

        while (i < randomNumberOfDays) {
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

    fun addToTaskList(name: String) {
        val newId = tasksList.size + 1
        tasksList.add(Task(id = newId, name = name))
    }

    fun getTask(taskId: Int): Task {
        return tasksList[taskId]
    }

    fun addSelection(taskId: Int, date: LocalDate){
        val currentMap = tasksList.elementAt(taskId).datesSelected.toMutableMap()
        val currentState = tasksList[taskId].datesSelected[date]
        var newState: Selection

        when(currentState) {
            Selection.BLANK -> newState = Selection.SELECTED
            Selection.SELECTED -> newState = Selection.UNSELECTED
            Selection.UNSELECTED -> newState = Selection.SELECTED
            else -> {
                newState = Selection.BLANK
            }
        }
        currentMap[date] = newState
        tasksList[taskId].datesSelected = currentMap

    }

}
