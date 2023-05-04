package com.dotcross_app.dotcross

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.dotcross_app.dotcross.data.Task
import com.dotcross_app.dotcross.ui.home.DotCrossHomeScreen
import com.dotcross_app.dotcross.ui.theme.DotCrossTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DotCrossTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val taskList: List<Task> = listOf<Task>(
                        Task(name = "Gym"),
                        Task(name = "Soccer"),
                        Task(name = "Climbing"),
                        Task(name = "Gym"),
                        Task(name = "Soccer"),
                        Task(name = "Climbing"),
                        Task(name = "Gym"),
                        Task(name = "Soccer"),
                        Task(name = "Climbing")
                    )
                    DotCrossHomeScreen(
                        navigateToItemEntry = { /*TODO*/ },
                        navigateToItemUpdate = {} )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    DotCrossTheme {

    }
}
