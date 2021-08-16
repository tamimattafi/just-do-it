package com.attafitamim.app.todo.di

import android.content.Context
import com.attafitamim.app.todo.di.modules.dataModule
import com.attafitamim.app.todo.di.modules.domainModule
import com.attafitamim.app.todo.di.modules.taskDetailsModule
import com.attafitamim.app.todo.di.modules.tasksListModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

object KoinInitializer {

    fun init(context: Context) {
        startKoin {
            androidContext(context)

            modules(
                dataModule,
                domainModule,
                tasksListModule,
                taskDetailsModule
            )
        }
    }
}