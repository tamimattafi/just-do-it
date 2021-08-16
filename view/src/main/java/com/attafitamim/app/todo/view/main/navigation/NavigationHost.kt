package com.attafitamim.app.todo.view.main.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.attafitamim.app.todo.view.tasks.details.model.TaskDetailsViewModel
import com.attafitamim.app.todo.view.tasks.details.widget.TaskDetails
import com.attafitamim.app.todo.view.tasks.list.widget.TasksList
import org.koin.androidx.compose.get
import org.koin.core.parameter.parametersOf

@Composable
fun MainNavigationHost(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = NavigationScreens.TASKS_LIST.screenName
    ) {
        composable(route = NavigationScreens.TASKS_LIST.rawRoute) {
            TasksList(viewModel = get(), navController)
        }

        composable(
            route = NavigationScreens.TASK_DETAILS.rawRoute,
            arguments = NavigationScreens.TASK_DETAILS.arguments
        ) { backStackEntry ->
            val arguments = requireNotNull(backStackEntry.arguments)
            val taskId = arguments.getInt(NavigationArguments.TASK_ID.key)
            val viewModel = get<TaskDetailsViewModel> {
                parametersOf(taskId)
            }

            TaskDetails(viewModel = viewModel)
        }
    }
}