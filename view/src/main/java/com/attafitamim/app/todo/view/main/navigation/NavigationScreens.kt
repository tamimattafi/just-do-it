package com.attafitamim.app.todo.view.main.navigation

enum class NavigationScreens(
    val screenName: String,
    val navigationArguments: List<NavigationArguments> = emptyList()
) {
    TASKS_LIST(screenName = "tasks_list"),
    TASK_DETAILS(
        screenName = "task_details",
        navigationArguments = listOf(NavigationArguments.TASK_ID)
    );

    val rawRoute get() = routeWithArgumentPaths(screenName, navigationArguments)
    val arguments get() = navigationArguments.map(NavigationArguments::argument)

    open fun withArguments(arguments: List<Any>) = routeWithArguments(screenName, arguments)
}
