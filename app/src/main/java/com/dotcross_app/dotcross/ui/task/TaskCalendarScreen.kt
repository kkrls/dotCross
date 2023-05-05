package com.dotcross_app.dotcross.ui.task

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
        SelectionControls(selectionState = calendarState.selectionState)
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

@Composable
private fun SelectionControls(
    selectionState: DynamicSelectionState,
) {
    Text(
        text = "Calendar Selection Mode",
        style = MaterialTheme.typography.h5,
    )
    SelectionMode.values().forEach { selectionMode ->
        Row(modifier = Modifier.fillMaxWidth()) {
            RadioButton(
                selected = selectionState.selectionMode == selectionMode,
                onClick = { selectionState.selectionMode = selectionMode }
            )
            Text(text = selectionMode.name)
            Spacer(modifier = Modifier.height(4.dp))
        }
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
        val date = Date(System.currentTimeMillis() - (1000 * 60 * 60 * 24))
        val date2 = Date(System.currentTimeMillis() - (1000 * 60 * 60 * 24) * 2)
        val dateMap = mutableMapOf(
            date to Selection.SELECTED,
            date2 to Selection.SELECTED
        )
        TaskCalendarBody(taskUiState = TaskUiState(
            name = "Gym", datesSelected = dateMap
        ))
    }
}
