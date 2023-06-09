package com.dotcross_app.dotcross.data

import java.time.LocalDate

// Data Class that represents a ask object. Uses a selection Enum on its map to represent the
// selection status of each day in the Map
data class Task(
    val id: Int = 0,
    val name: String,
    var datesSelected: Map<LocalDate, Selection> = mutableMapOf<LocalDate, Selection>()
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