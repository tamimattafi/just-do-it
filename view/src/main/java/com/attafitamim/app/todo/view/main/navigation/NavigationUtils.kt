package com.attafitamim.app.todo.view.main.navigation

import androidx.navigation.NavController
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.navArgument
import java.lang.StringBuilder

const val ARGUMENT_PATH_PREFIX = "{"
const val ARGUMENT_PATH_POSTFIX = "}"
const val ROUTE_SEPARATOR = "/"

fun argumentPath(key: String) =
    StringBuilder().append(
        ARGUMENT_PATH_PREFIX,
        key,
        ARGUMENT_PATH_POSTFIX
    )

fun routeWithArgumentPaths(
    screenName: String,
    navigationArguments: List<NavigationArguments>
): String {
    val rawRouteBuilder = StringBuilder(screenName)

    navigationArguments.forEach { argument ->
        rawRouteBuilder.append(
            ROUTE_SEPARATOR,
            argumentPath(key = argument.key)
        )
    }

    return rawRouteBuilder.toString()
}

fun routeWithArguments(screenName: String, arguments: List<Any>): String {
    val rawRouteBuilder = StringBuilder(screenName)

    arguments.forEach { argument ->
        rawRouteBuilder.append(
            ROUTE_SEPARATOR,
            argument
        )
    }

    return rawRouteBuilder.toString()
}

fun intArgument(key: String) =
    navArgument(name = key) { type = NavType.IntType }

fun NavController.navigate(
    screen: NavigationScreens,
) = navigate(screen.screenName)

fun NavController.navigate(
    screen: NavigationScreens,
    builder: NavOptionsBuilder.() -> Unit
) = navigate(screen.screenName, builder)

fun NavController.navigate(
    screen: NavigationScreens,
    vararg arguments: Any,
) {
    val route = screen.withArguments(arguments.toList())
    navigate(route)
}

fun NavController.navigate(
    screen: NavigationScreens,
    builder: NavOptionsBuilder.() -> Unit,
    vararg arguments: Any,
) {
    val route = screen.withArguments(arguments.toList())
    navigate(route, builder)
}