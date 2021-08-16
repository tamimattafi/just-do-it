package com.attafitamim.app.todo.view.main.navigation

import androidx.navigation.NavType
import androidx.navigation.compose.navArgument
import java.lang.StringBuilder

fun keyRoute(key: String) =
    StringBuilder().append(
        "{",
        key,
        "}"
    )

fun argumentRoute(route: String, key: String) =
    StringBuilder().append(
        route,
        "/",
        keyRoute(key = key)
    ).toString()

fun intArgument(key: String) = navArgument(name = key) { type = NavType.IntType }
