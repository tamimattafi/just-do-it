package com.attafitamim.app.todo.di.modules

import com.attafitamim.app.todo.data.local.db.provider.DatabaseProvider
import com.attafitamim.app.todo.data.local.source.TasksLocalDataSource
import com.attafitamim.app.todo.data.remote.client.provider.HttpClientProvider
import com.attafitamim.app.todo.data.remote.source.TasksRemoteDataSource
import com.attafitamim.app.todo.di.providers.RoomDatabaseProvider
import com.attafitamim.app.todo.domain.repository.source.ITasksLocalDataSource
import com.attafitamim.app.todo.domain.repository.source.ITasksRemoteDataSource
import org.koin.dsl.module

val dataModule = module {

    single { HttpClientProvider() }

    single<ITasksRemoteDataSource> { TasksRemoteDataSource(get()) }

    single<DatabaseProvider> {
        RoomDatabaseProvider(get())
    }

    single<ITasksLocalDataSource> { TasksLocalDataSource(get()) }
}
