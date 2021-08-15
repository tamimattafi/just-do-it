package com.attafitamim.app.todo.di

import androidx.room.Room
import androidx.room.RoomDatabase
import com.attafitamim.app.todo.data.local.source.TasksLocalDataSource
import com.attafitamim.app.todo.data.local.db.provider.DatabaseProvider
import com.attafitamim.app.todo.data.remote.client.provider.HttpClientProvider
import com.attafitamim.app.todo.data.remote.source.TasksRemoteDataSource
import com.attafitamim.app.todo.domain.repository.ITasksRepository
import com.attafitamim.app.todo.domain.repository.impl.TasksRepository
import com.attafitamim.app.todo.domain.repository.source.ITasksLocalDataSource
import com.attafitamim.app.todo.domain.repository.source.ITasksRemoteDataSource
import com.attafitamim.app.todo.domain.usecase.GetTask
import com.attafitamim.app.todo.domain.usecase.GetTasksPage
import org.koin.dsl.module

object KoinModules {

    val domainModule = module {

        single { GetTasksPage(get()) }

        single { GetTask(get()) }

        single<ITasksRepository> { TasksRepository(get(), get(), get()) }
    }

    val dataModule = module {

        single { HttpClientProvider() }

        single<ITasksRemoteDataSource> { TasksRemoteDataSource(get()) }

        single {
            object : DatabaseProvider() {
                override fun <T : RoomDatabase> createDatabase(name: String, clazz: Class<T>): T {
                    return Room.databaseBuilder(get(), clazz, name).build()
                }
            }
        }

        single<ITasksLocalDataSource> { TasksLocalDataSource(get()) }
    }
}
