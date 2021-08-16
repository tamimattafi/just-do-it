package com.attafitamim.app.todo.view.main

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.attafitamim.app.todo.view.main.navigation.MainNavigationHost
import com.attafitamim.app.todo.view.main.navigation.NavigationScreens
import com.attafitamim.app.todo.view.tasks.list.widget.TasksList
import org.koin.androidx.compose.get

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val navController = rememberNavController()
            MainNavigationHost(navController)
        }
    }
}