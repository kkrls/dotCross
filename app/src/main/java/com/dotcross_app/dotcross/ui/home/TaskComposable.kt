package com.dotcross_app.dotcross.ui.home

import android.content.ClipData
import android.content.ClipData.Item
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dotcross_app.dotcross.R
import com.dotcross_app.dotcross.data.Task
import com.dotcross_app.dotcross.ui.theme.DotCrossTheme
import java.util.TimerTask


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
            .clickable { onTaskClick }
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
                    .padding(vertical = 16.dp)
            ) {
                Image(
                    painter = painterResource(
                        id = R.drawable.green_circle
                    ),
                    contentDescription = "",
                    modifier = Modifier
                        .weight(1.2f)
                        .clip(CircleShape)
                )
                Image(
                    painter = painterResource(
                        id = R.drawable.red_circle
                    ),
                    contentDescription = "",
                    modifier = Modifier
                        .weight(1.2f)
                )
                Image(
                    painter = painterResource(
                        id = R.drawable.blank_circle
                    ),
                    contentDescription = "",
                    modifier = Modifier
                        .weight(1.2f)
                )
                Image(
                    painter = painterResource(
                        id = R.drawable.arrow_icon
                    ),
                    contentDescription = "",
                    modifier = Modifier
                        .weight(1.2f)
                )
            }
        }
    }
}

@Composable
private fun DotCrossTopBar(modifier: Modifier = Modifier) {
    Row(modifier = Modifier
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
    onItemClick: (Task) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = Modifier.padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(taskList) { task ->
            TaskView(task = task, onTaskClick = {})
        }
    }
}

@Composable
private fun HomeBodyContent(
    taskList: List<Task>,
    onItemClick: (Int) -> Unit,
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
            TaskList(taskList = taskList, onItemClick = {})
        }
    }
}

@Composable
fun DotCrossHomeScreen(
    navigateToItemEntry: () -> Unit,
    navigateToItemUpdate: (Int) -> Unit,
    modifier: Modifier = Modifier,
    taskList: List<Task>
    //replace with viewmodel
) {
    Scaffold(
        topBar = {
            //Navigate Back function to implement
            DotCrossTopBar()
        } ,
        floatingActionButton = {
            ExtendedFloatingActionButton(
                onClick = navigateToItemEntry,
                modifier = Modifier.navigationBarsPadding(),
                text = { Text(text = stringResource(R.string.add_task))},
                icon = {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "",
                        tint = MaterialTheme.colors.onPrimary
                    )
                }
            )
        },
    ) { innerPadding ->
        HomeBodyContent(
            taskList = taskList,
            onItemClick = {},
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
            val taskList: List<Task> = listOf<Task>(
                Task(name = "Gym", date = null),
                Task(name = "Soccer", date = null),
                Task(name = "Sex", date = null),
            )
            DotCrossHomeScreen(
                navigateToItemEntry = { /*TODO*/ },
                navigateToItemUpdate = {},
                taskList = taskList
            )
        }

    }
}