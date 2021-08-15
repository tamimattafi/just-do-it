package com.attafitamim.app.todo.di

import android.content.Context
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

object KoinInitializer {

    fun init(context: Context) {
        startKoin {
            androidContext(context)
            modules(
                KoinModules.dataModule,
                KoinModules.domainModule
            )
        }
    }
}