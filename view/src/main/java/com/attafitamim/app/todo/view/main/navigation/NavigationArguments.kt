package com.attafitamim.app.todo.view.main.navigation

import androidx.navigation.compose.NamedNavArgument
import com.attafitamim.app.todo.view.main.navigation.NavigationArguments.Keys.TASK_ID_KEY

enum class NavigationArguments(val key: String) {
    TASK_ID(key = TASK_ID_KEY) {
        override val argument get() = intArgument(key)
    };

    abstract val argument: NamedNavArgument

    object Keys {
        const val TASK_ID_KEY = "task_id"
    }
}