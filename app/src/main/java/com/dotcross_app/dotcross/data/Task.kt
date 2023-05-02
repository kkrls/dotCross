package com.dotcross_app.dotcross.data

import java.util.Date

data class Task(
    val id: Int = 0,
    val name:String,
    val datesSelected: Map<Date, Selection> = mapOf<Date, Selection>()
)

enum class Selection {
    BLANK, SELECTED, UNSELECTED
}