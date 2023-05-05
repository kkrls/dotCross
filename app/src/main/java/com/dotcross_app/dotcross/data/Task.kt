package com.dotcross_app.dotcross.data

import java.sql.Date

data class Task(
    val id: Int = 0,
    val name:String,
    val datesSelected: Map<Date, Selection> = mutableMapOf<Date, Selection>()
)

enum class Selection {
    BLANK, SELECTED, UNSELECTED
}