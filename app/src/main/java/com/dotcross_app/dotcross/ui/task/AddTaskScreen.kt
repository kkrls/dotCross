package com.dotcross_app.dotcross.ui.task

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dotcross_app.dotcross.ui.theme.DotCrossTheme
import com.dotcross_app.dotcross.DotCrossTopAppBar
import com.dotcross_app.dotcross.R

@Composable
private fun TaskTextForm(
    taskUiState: TaskUiState,
    modifier: Modifier = Modifier,
    onValueChange: (TaskUiState) -> Unit = {},
    enabled: Boolean = true
) {
    OutlinedTextField(
        value = taskUiState.name,
        onValueChange = { onValueChange(taskUiState.copy(name = it)) },
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        enabled = enabled,
        singleLine = true,
        label = { Text(stringResource(R.string.add_task_text_label))}
    )
}

@Composable
fun AddTaskBody(
    taskUiState: TaskUiState,
    onItemValueChange: (TaskUiState) -> Unit,
    onSaveClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        TaskTextForm(
            taskUiState = taskUiState,
            onValueChange = onItemValueChange
        )
        Button(modifier = Modifier
            .fillMaxWidth(),
            onClick = onSaveClick,
            enabled = taskUiState.enabled
        ) {
            Text(text = stringResource(R.string.create_task_button))
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun AddTaskScreenPreview() {
    DotCrossTheme {
        AddTaskBody(
            taskUiState = TaskUiState(),
            modifier = Modifier,
            onItemValueChange = {},
            onSaveClick = {}
        )
    }
}