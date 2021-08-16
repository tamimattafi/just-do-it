package com.attafitamim.app.todo.di

import com.attafitamim.app.todo.components.managers.ConnectionManager
import com.attafitamim.app.todo.data.local.source.TasksLocalDataSource
import com.attafitamim.app.todo.data.local.db.provider.DatabaseProvider
import com.attafitamim.app.todo.data.remote.client.provider.HttpClientProvider
import com.attafitamim.app.todo.data.remote.source.TasksRemoteDataSource
import com.attafitamim.app.todo.di.providers.RoomDatabaseProvider
import com.attafitamim.app.todo.domain.repository.ITasksRepository
import com.attafitamim.app.todo.domain.repository.impl.TasksRepository
import com.attafitamim.app.todo.domain.repository.source.ITasksLocalDataSource
import com.attafitamim.app.todo.domain.repository.source.ITasksRemoteDataSource
import com.attafitamim.app.todo.domain.usecase.GetTask
import com.attafitamim.app.todo.domain.usecase.GetTasksPage
import com.attafitamim.app.todo.domain.utils.IConnectionManager
import com.attafitamim.app.todo.view.tasks.list.model.TasksListViewModel
import com.attafitamim.app.todo.view.tasks.list.source.TasksSource
import org.koin.dsl.module

object KoinModules {

    val domainModule = module {

        single { GetTasksPage(get()) }

        single { GetTask(get()) }

        single<ITasksRepository> { TasksRepository(get(), get(), get()) }

        single<IConnectionManager> { ConnectionManager(get()) }
    }

    val dataModule = module {

        single { HttpClientProvider() }

        single<ITasksRemoteDataSource> { TasksRemoteDataSource(get()) }

        single<DatabaseProvider> {
            RoomDatabaseProvider(get())
        }

        single<ITasksLocalDataSource> { TasksLocalDataSource(get()) }
    }

    val viewModule = module {

        single {
            TasksListViewModel(get(), get())
        }

        single {
            TasksSource(get())
        }
    }
}
