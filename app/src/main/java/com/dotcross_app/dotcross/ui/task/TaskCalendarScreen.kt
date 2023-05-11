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


import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dotcross_app.dotcross.DotCrossTopAppBar
import com.dotcross_app.dotcross.R
import com.dotcross_app.dotcross.data.Selection
import com.dotcross_app.dotcross.data.TasksRepository
import com.dotcross_app.dotcross.ui.navigation.NavigationDestination
import com.dotcross_app.dotcross.ui.theme.DotCrossTheme
import io.github.boguszpawlowski.composecalendar.SelectableCalendar
import io.github.boguszpawlowski.composecalendar.day.Day
import io.github.boguszpawlowski.composecalendar.day.DayState
import io.github.boguszpawlowski.composecalendar.rememberSelectableCalendarState
import kotlinx.coroutines.launch

object TaskCalendarDestination : NavigationDestination {
    override val route = "task_calendar"
    override val title = R.string.task_calendar_title
    const val taskId = "taskId"
    val routeWithArgs = "$route/{$taskId}"
}

var taskIdLocal: Int = 0

@Composable
fun TaskCalendarScreen(
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier,
    onNavigateUp: () -> Unit,
    navigateBackEnabled: Boolean = true,
    taskId: Int?
) {
    if (taskId != null) {
        taskIdLocal = taskId
    }
    Scaffold(
        topBar = {
            DotCrossTopAppBar(
                text = stringResource(id = TaskCalendarDestination.title),
                backEnabled = navigateBackEnabled,
                navigateUp = onNavigateUp
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(innerPadding),
            verticalArrangement = Arrangement.spacedBy(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = taskId?.let { TasksRepository().getTask(it).name } ?: "Task",
                style = MaterialTheme.typography.h1,
                modifier = Modifier
                    .padding(vertical = 10.dp, horizontal = 12.dp)
            )
            TaskCalendarBody(taskId = taskId)
        }
    }
}

@Composable
private fun TaskCalendarBody(
    modifier: Modifier = Modifier,
    taskId: Int?
) {
    val calendarState = rememberSelectableCalendarState()
    Column(
        modifier = Modifier.verticalScroll(rememberScrollState())
    ) {
        SelectableCalendar(
            modifier = Modifier
                .padding(8.dp)
            ,
            calendarState = calendarState,
            dayContent = { dayState -> DayView(dayState) }
        )
        Divider(modifier.padding(12.dp))
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 48.dp, vertical = 4.dp),
            onClick = {},
            enabled = true,
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color(0xFFBDBDBD)
            )
        ) {
            Text(text = stringResource(R.string.change_task_name_button))
        }
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 48.dp, vertical = 4.dp),
            onClick = {},
            enabled = true,
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color(0xFFBDBDBD)
            )
        ) {
            Text(text = stringResource(R.string.clear_month_button))
        }
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 48.dp, vertical = 4.dp),
            onClick = {},
            enabled = true,
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color(0xFFBDBDBD)
            )
        ) {
            Text(text = stringResource(R.string.delete_task_button))
        }
    }
}

@Composable
fun DayView(dayState: Day) {
    val dayFromList = TasksRepository().getTask(taskIdLocal)
    //val selection by remember {mutableStateOf(Selection)}

    val color = when (dayFromList.datesSelected[dayState.date]) {
        Selection.SELECTED -> Color(0xFF1EB100)
        Selection.UNSELECTED -> Color(0xFFED230D)
        else -> {
            Color(0xFFFFFFFF)
        }
    }

    Box(
        modifier = Modifier
            .shadow(
                elevation = 10.dp,
                shape = RoundedCornerShape(8.dp)
            )
            .fillMaxWidth()
            .background(color)
            .padding(10.dp)
            .clickable {
                TasksRepository().addSelection(taskIdLocal, dayState.date)
                println(TasksRepository().getTask(taskId = taskIdLocal).datesSelected)
            }
        ,
        contentAlignment = Alignment.Center,

    ) {
        Text(dayState.date.dayOfMonth.toString())
    }
}


@Preview(showBackground = true)
@Composable
fun TaskCalendarScreenPreview() {
    DotCrossTheme {

    }
}
