package com.attafitamim.app.todo.view.main.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.attafitamim.app.todo.view.tasks.details.widget.TaskDetails
import com.attafitamim.app.todo.view.tasks.list.widget.TasksList
import org.koin.androidx.compose.get

@Composable
fun MainNavigationHost(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = NavigationScreens.TASKS_LIST.route
    ) {
        composable(route = NavigationScreens.TASKS_LIST.route) {
            TasksList(viewModel = get())
        }

        composable(
            route = NavigationScreens.TASK_DETAILS.route,
            arguments = listOf(NavigationArguments.TASK_ID.argument)
        ) { backStackEntry ->
            val arguments = requireNotNull(backStackEntry.arguments)
            val taskId = arguments.getInt(NavigationArguments.TASK_ID.key)
            TaskDetails(viewModel = get(), taskId)
        }
    }
}