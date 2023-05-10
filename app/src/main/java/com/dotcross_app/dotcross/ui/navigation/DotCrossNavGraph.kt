package com.dotcross_app.dotcross.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.dotcross_app.dotcross.data.Task
import com.dotcross_app.dotcross.data.TasksRepository
import com.dotcross_app.dotcross.ui.home.DotCrossHomeScreen
import com.dotcross_app.dotcross.ui.home.HomeDestination
import com.dotcross_app.dotcross.ui.task.AddTaskDestination
import com.dotcross_app.dotcross.ui.task.AddTaskScreen
import com.dotcross_app.dotcross.ui.task.TaskCalendarDestination
import com.dotcross_app.dotcross.ui.task.TaskCalendarScreen

@Composable
fun DotCrossNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    taskList: MutableList<Task> = TasksRepository().getTaskList()
){
    NavHost(
        navController = navController,
        startDestination = HomeDestination.route,
        modifier = modifier
    ) {
        composable(route = HomeDestination.route) {
            DotCrossHomeScreen(
                taskList = taskList,
                navigateToTaskEntry = { navController.navigate(AddTaskDestination.route) },
                navigateToCalendarView = {
                    navController.navigate("${TaskCalendarDestination.route}/${it}")
                }
            )
        }
        composable(route = AddTaskDestination.route){
            AddTaskScreen(
                navigateBack = { navController.popBackStack() },
                onNavigateUp = { navController.navigateUp() })
        }
        composable(
            route = TaskCalendarDestination.routeWithArgs,
            arguments = listOf(navArgument(TaskCalendarDestination.taskId) {
                type = NavType.IntType
            })
        ) { backStackEntry ->
            TaskCalendarScreen(
                navigateBack = { navController.navigateUp()},
                taskId = backStackEntry.arguments?.getInt("taskId")
            )
        }
    }
}