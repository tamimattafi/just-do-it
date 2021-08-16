package com.attafitamim.app.todo.di.modules

import com.attafitamim.app.todo.components.managers.ConnectionManager
import com.attafitamim.app.todo.domain.repository.ITasksRepository
import com.attafitamim.app.todo.domain.repository.impl.TasksRepository
import com.attafitamim.app.todo.domain.usecase.GetTask
import com.attafitamim.app.todo.domain.usecase.GetTasksPage
import com.attafitamim.app.todo.domain.utils.IConnectionManager
import org.koin.dsl.module

val domainModule = module {

    single { GetTasksPage(get()) }

    single { GetTask(get()) }

    single<ITasksRepository> { TasksRepository(get(), get(), get()) }

    factory<IConnectionManager> { ConnectionManager(get()) }
}

