package com.attafitamim.app.todo.view.main.navigation

import com.attafitamim.app.todo.view.main.navigation.NavigationScreens.Routes.TASKS_LIST_ROUTE
import com.attafitamim.app.todo.view.main.navigation.NavigationScreens.Routes.TASK_DETAILS_ROUTE

enum class NavigationScreens(val route: String) {
    TASKS_LIST(route = TASKS_LIST_ROUTE),
    TASK_DETAILS(route = argumentRoute(
        TASK_DETAILS_ROUTE,
        NavigationArguments.TASK_ID.key
    ));

    object Routes {
        const val TASKS_LIST_ROUTE = "tasks_list"
        const val TASK_DETAILS_ROUTE = "task_details"
    }
}
