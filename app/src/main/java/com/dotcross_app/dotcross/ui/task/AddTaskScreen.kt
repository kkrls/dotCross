package com.dotcross_app.dotcross.ui.task

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.dotcross_app.dotcross.DotCrossTopAppBar
import com.dotcross_app.dotcross.R
import com.dotcross_app.dotcross.ui.navigation.NavigationDestination
import com.dotcross_app.dotcross.ui.theme.DotCrossTheme
import kotlinx.coroutines.launch

// Companion object used for the NavGraph
object AddTaskDestination : NavigationDestination {
    override val route = "add_task"
    override val title = R.string.add_task_name
}

// Composable to represent the App's screen to add a new task
@Composable
fun AddTaskScreen(
    modifier: Modifier = Modifier,
    navigateBack: () -> Unit,
    onNavigateUp: () -> Unit,
    navigateBackEnabled: Boolean = true,
    viewModel: AddTaskViewModel = viewModel()
) {
    val name = viewModel.name.collectAsState()
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current
    Scaffold(
        topBar = {
            DotCrossTopAppBar(
                text = stringResource(id = AddTaskDestination.title),
                backEnabled = navigateBackEnabled,
                navigateUp = onNavigateUp
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(innerPadding),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            OutlinedTextField(
                value = name.value,
                onValueChange = viewModel::setName,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                singleLine = true,
                label = { Text(stringResource(R.string.add_task_text_label)) }
            )
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                onClick = {
                    coroutineScope.launch {
                        viewModel::saveTask
                        Toast.makeText(context, "Task added!", Toast.LENGTH_LONG).show()
                        navigateBack()
                    }
                },
                enabled = true,
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color(0xFFBDBDBD)
                )
            ) {
                Text(text = stringResource(R.string.create_task_button))
            }
        }

    }
}


@Preview(showBackground = true)
@Composable
private fun AddTaskScreenPreview() {
    DotCrossTheme {

    }
}


