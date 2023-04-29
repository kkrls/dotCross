package com.dotcross_app.dotcross.ui.home

import android.content.ClipData.Item
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
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
    onTaskClick: (Item) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .padding(16.dp)
    ) {
        Text(
            text = task.name,
            fontWeight = FontWeight.Bold,
            fontSize = 32.sp
        )
        Text(
            text = stringResource(id = R.string.last_three_days),
            fontSize = 24.sp
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


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    DotCrossTheme {
        TaskView(task = Task(name = "Gym"), onTaskClick = {})
    }
}