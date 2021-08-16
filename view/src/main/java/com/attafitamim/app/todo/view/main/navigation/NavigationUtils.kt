package com.attafitamim.app.todo.view.main.navigation

import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.navArgument
import java.lang.StringBuilder

fun keyRoute(key: String) =
    StringBuilder().append(
        "{",
        key,
        "}"
    )

fun rawRoute(route: String, key: String) =
    StringBuilder().append(
        route,
        "/",
        keyRoute(key = key)
    ).toString()

fun argumentRoute(route: String, argument: Any?) =
    StringBuilder().append(
        route,
        "/",
        argument?.toString().orEmpty()
    ).toString()

fun intArgument(key: String) =
    navArgument(name = key) { type = NavType.IntType }

fun NavController.navigate(
    screen: NavigationScreens,
    builder: NavOptionsBuilder.() -> Unit
) = navigate(screen.screenName, builder)

fun NavController.navigate(
    screen: NavigationScreens,
    argument: Any?,
    builder: NavOptionsBuilder.() -> Unit = {}
) {
    val route = screen.argumentRoute(argument)
    navigate(route, builder)
}