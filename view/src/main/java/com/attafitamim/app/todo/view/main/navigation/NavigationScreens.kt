package com.attafitamim.app.todo.view.main.navigation

import androidx.navigation.compose.NamedNavArgument
import com.attafitamim.app.todo.view.main.navigation.NavigationScreens.Routes.TASKS_LIST_ROUTE
import com.attafitamim.app.todo.view.main.navigation.NavigationScreens.Routes.TASK_DETAILS_ROUTE

enum class NavigationScreens(
    val screenName: String,
    val rawRoute: String = screenName,
    val arguments: List<NamedNavArgument> = emptyList()
) {
    TASKS_LIST(screenName = TASKS_LIST_ROUTE),
    TASK_DETAILS(
        screenName = TASK_DETAILS_ROUTE,
        rawRoute = rawRoute(
            TASK_DETAILS_ROUTE,
            NavigationArguments.TASK_ID.key
        ),
        arguments = listOf(NavigationArguments.TASK_ID.argument)
    );

    open fun argumentRoute(argument: Any?) = argumentRoute(screenName, argument)

    object Routes {
        const val TASKS_LIST_ROUTE = "tasks_list"
        const val TASK_DETAILS_ROUTE = "task_details"
    }
}
