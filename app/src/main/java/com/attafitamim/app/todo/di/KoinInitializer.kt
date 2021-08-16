package com.attafitamim.app.todo.di

import android.content.Context
import com.attafitamim.app.todo.di.KoinModules.dataModule
import com.attafitamim.app.todo.di.KoinModules.domainModule
import com.attafitamim.app.todo.di.KoinModules.viewModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

object KoinInitializer {

    fun init(context: Context) {
        startKoin {
            androidContext(context)

            modules(
                dataModule,
                domainModule,
                viewModule
            )
        }
    }
}