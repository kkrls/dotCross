package com.dotcross_app.dotcross.data

import java.time.LocalDate
import kotlin.random.Random

class TasksRepository() {

    private var taskList = mutableListOf(
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

    fun getTaskList(): List<Task> {
        return taskList
    }

    fun addToTaskList(name: String){
        taskList.add(Task(id = taskList.size + 1, name = name))
    }
}
