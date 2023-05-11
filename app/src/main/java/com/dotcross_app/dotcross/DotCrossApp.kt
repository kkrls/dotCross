package com.dotcross_app.dotcross

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.dotcross_app.dotcross.data.TasksRepository
import com.dotcross_app.dotcross.ui.navigation.DotCrossNavHost

// Home Method to launch app from MainActivity. App is launched using NavHostController that uses
// the routes defined for every screen
@Composable
fun DotCrossApp(navController: NavHostController = rememberNavController()) {
    DotCrossNavHost(
        navController = navController
    )
}

