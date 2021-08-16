package com.attafitamim.app.todo.components

import android.app.Application
import com.attafitamim.app.todo.di.KoinInitializer

class TodoApp : Application() {

    override fun onCreate() {
        super.onCreate()
        KoinInitializer.init(this)
    }
}