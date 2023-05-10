package com.dotcross_app.dotcross.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.dotcross_app.dotcross.R
import com.dotcross_app.dotcross.data.Selection
import com.dotcross_app.dotcross.data.Task
import com.dotcross_app.dotcross.data.TasksRepository
import com.dotcross_app.dotcross.ui.navigation.NavigationDestination
import com.dotcross_app.dotcross.ui.theme.DotCrossTheme
import java.sql.Date
import java.time.LocalDate


object HomeDestination : NavigationDestination {
    override val route = "home"
    override val title = R.string.app_name
}

private fun getLastThreeDaysBeforeToday(): List<LocalDate> {
    var dates = mutableListOf<LocalDate>()

    for (i in 1..3){
        val localDate = LocalDate.now()
        dates.add(localDate.minusDays(i.toLong()))
    }

    return dates
}
 private fun getTaskImageResource(selection: Selection? ) : Int {

    val imageResource = when(selection) {
        Selection.SELECTED -> R.drawable.green_circle
        Selection.UNSELECTED -> R.drawable.red_circle
        else -> {
            R.drawable.blank_circle
        }
    }

    return imageResource
}

@Composable
fun TaskImages(
    modifier: Modifier = Modifier,
    task : Task
) {

    val lastThreeDays = getLastThreeDaysBeforeToday()
    var selectionList = mutableListOf<Selection?>()

    for (day in lastThreeDays){
        if (day in task.datesSelected.keys){
            selectionList.add(task.datesSelected[day])
        } else {
            selectionList.add(Selection.BLANK)
        }
    }

    Row( modifier = Modifier
        .fillMaxWidth()
    ) {
        Image(
            painter = painterResource(
                id = getTaskImageResource(selectionList[2])
            ),
            contentDescription = "",
            modifier = Modifier
                .weight(1f)
        )
        Image(
            painter = painterResource(
                id = getTaskImageResource(selectionList[1])
            ),
            contentDescription = "",
            modifier = Modifier
                .weight(1f)
        )
        Image(
            painter = painterResource(
                id = getTaskImageResource(selectionList[0])
            ),
            contentDescription = "",
            modifier = Modifier
                .weight(1f)
        )
        Image(
            painter = painterResource(
                id = R.drawable.arrow_icon
            ),
            contentDescription = "",
            modifier = Modifier
                .weight(1f)
        )
    }
}

@Composable
private fun TaskView(
    task: Task,
    onTaskClick: (Task) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = Modifier
            .padding(4.dp)
            .shadow(
                elevation = 4.dp,
                shape = RoundedCornerShape(32.dp)
            )
            .fillMaxWidth()
            .clickable { onTaskClick(task) }
    ) {
        Column() {
            Text(
                text = task.name,
                style = MaterialTheme.typography.h1,
                modifier = Modifier
                    .padding(vertical = 1.dp, horizontal = 12.dp)
            )
            Text(
                text = stringResource(id = R.string.last_three_days),
                style = MaterialTheme.typography.body1,
                modifier = Modifier
                    .padding(horizontal = 12.dp)
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                horizontalArrangement = Arrangement.Start
            ) {
                TaskImages(
                    task = task,
                    modifier = Modifier
                )
            }
        }
    }
}

@Composable
private fun DotCrossTopBar(modifier: Modifier = Modifier) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = MaterialTheme.colors.background),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            modifier = Modifier
                .size(52.dp)
                .padding(4.dp),
            painter = painterResource(id = R.drawable.dotcross_icon),
            contentDescription = ""
        )
        Text(
            text = stringResource(id = R.string.app_name),
            style = MaterialTheme.typography.h1
        )
    }
}

@Composable
private fun TaskList(
    taskList: List<Task>,
    onTaskClick: (Task) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = Modifier.padding(4.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(items = taskList, key = {it.id}) { task ->
            TaskView(task = task, onTaskClick = onTaskClick )
        }
    }
}

@Composable
fun HomeBodyContent(
    taskList: List<Task>,
    onTaskClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        if (taskList.isEmpty()) {
            Text(
                text = stringResource(R.string.empty_list),
                style = MaterialTheme.typography.body1,
            )
        } else {
            TaskList(taskList = taskList, onTaskClick = { onTaskClick(it.id)} )
        }
    }
}

@Composable
fun DotCrossHomeScreen(
    navigateToTaskEntry: () -> Unit,
    navigateToCalendarView: (Int) -> Unit,
    modifier: Modifier = Modifier,
    taskList: MutableList<Task>,
    homeViewModel: HomeViewModel = viewModel()
) {
    val homeUiState by homeViewModel.uiState.collectAsState()
    Scaffold(
        topBar = {
            DotCrossTopBar()
        },
        floatingActionButton = {
            ExtendedFloatingActionButton(
                onClick = navigateToTaskEntry,
                modifier = Modifier.navigationBarsPadding(),
                text = {
                    Text(
                        text = stringResource(R.string.add_task),
                        style = MaterialTheme.typography.body1
                    )
                },
                contentColor = MaterialTheme.colors.onSurface,
                backgroundColor = MaterialTheme.colors.onPrimary,
                icon = {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "",
                        tint = MaterialTheme.colors.onSurface
                    )
                }
            )
        },
    ) { innerPadding ->
        HomeBodyContent(
            taskList = taskList,
            onTaskClick = navigateToCalendarView,
            modifier = modifier.padding(innerPadding)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    DotCrossTheme {
        Column(
            modifier = Modifier.padding(32.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            HomeBodyContent(taskList = TasksRepository().getTaskList(), onTaskClick = {})
        }

    }
}