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
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
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
fun TaskList(
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
private fun TaskView(
    task: Task,
    onTaskClick: (Task) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = Modifier
            .shadow(
                elevation = 10.dp,
                shape = RoundedCornerShape(8.dp)
            )
            .fillMaxWidth()
            .background(Color.White)
            .clickable { onTaskClick }
            //.padding(10.dp)
    ) {
        Column() {
            Text(
                text = task.name,
                fontWeight = FontWeight.Bold,
                fontSize = 32.sp,
                modifier = Modifier
                    .padding(vertical = 1.dp, horizontal = 12.dp)
            )
            Text(
                text = stringResource(id = R.string.last_three_days),
                fontSize = 24.sp,
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


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    DotCrossTheme {
        Column(
            modifier = Modifier.padding(32.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            val TaskList: List<Task> = listOf<Task>(
                Task(name = "Gym", date = null),
                Task(name = "Soccer", date = null),
                Task(name = "Sex", date = null),
            )
            TaskList(
                TaskList, onItemClick = {}
            )
        }

    }
}