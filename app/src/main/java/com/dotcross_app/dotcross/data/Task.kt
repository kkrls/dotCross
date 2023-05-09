package com.dotcross_app.dotcross.data

import java.time.LocalDate

data class Task(
    val id: Int = 0,
    val name:String,
    val datesSelected: Map<LocalDate, Selection> = mutableMapOf<LocalDate, Selection>()
)

inline fun <reified T : Enum<T>> random(): T = enumValues<T>().random()

enum class Selection {
    BLANK, SELECTED, UNSELECTED;

    companion object {
        fun randomValue(): Selection {
            return random()
        }
    }
}