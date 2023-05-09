package com.dotcross_app.dotcross.ui.task
/* for library: io.github.boguszpawlowski.composecalendar
*
*   Copyright 2022 Bogusz Pawłowski
*
*   Licensed under the Apache License, Version 2.0 (the "License");
*   you may not use this file except in compliance with the License.
*   You may obtain a copy of the License at
*
*      http://www.apache.org/licenses/LICENSE-2.0
*
*   Unless required by applicable law or agreed to in writing, software
*   distributed under the License is distributed on an "AS IS" BASIS,
*   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
*   See the License for the specific language governing permissions and
*   limitations under the License.
*
 */


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.RadioButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dotcross_app.dotcross.data.Selection
import com.dotcross_app.dotcross.ui.theme.DotCrossTheme
import io.github.boguszpawlowski.composecalendar.SelectableCalendar
import io.github.boguszpawlowski.composecalendar.day.DayState
import io.github.boguszpawlowski.composecalendar.rememberSelectableCalendarState
import io.github.boguszpawlowski.composecalendar.selection.DynamicSelectionState
import io.github.boguszpawlowski.composecalendar.selection.SelectionMode
import java.sql.Date
import java.time.LocalDate

@Composable
private fun TaskCalendarBody(
    modifier: Modifier = Modifier,
    taskUiState: TaskUiState,
) {
    val calendarState = rememberSelectableCalendarState()
    Column(
        modifier = Modifier.verticalScroll(rememberScrollState())
    ) {
        SelectableCalendar(calendarState = calendarState)
    }
}

@Composable
fun DayView(
    selectionState: DynamicSelectionState,
    selectionUiState: SelectionUiState
) {
    selectionState.selectionMode == SelectionMode.Multiple
    val backgroundColour = when (selectionUiState.selection) {
        Selection.SELECTED -> Color(0xFF1EB100)
        Selection.UNSELECTED -> Color(0xFFED230D)
        Selection.BLANK -> Color(0xFFFFFFFF)
    }

    Box(modifier = Modifier
        .fillMaxWidth()
        .background(backgroundColour)
    ) {
        Text("")
    }

    Text(
        text = "Selection: ${selectionState.selection.joinToString { it.toString() }}",
        style = MaterialTheme.typography.h6,
    )
}


@Preview(showBackground = true)
@Composable
fun TaskCalendarScreenPreview () {
    DotCrossTheme {
        val date = LocalDate.now().minusDays(1)
        val date2 = LocalDate.now().minusDays(2)
        val dateMap = mutableMapOf(
            date to Selection.SELECTED,
            date2 to Selection.SELECTED
        )
        TaskCalendarBody(taskUiState = TaskUiState(
            name = "Gym", datesSelected = dateMap
        ))
    }
}
