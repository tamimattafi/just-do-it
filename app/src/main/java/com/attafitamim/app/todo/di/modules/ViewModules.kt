package com.attafitamim.app.todo.di.modules

import com.attafitamim.app.todo.view.tasks.details.model.TaskDetailsViewModel
import com.attafitamim.app.todo.view.tasks.list.model.TasksListViewModel
import com.attafitamim.app.todo.view.tasks.list.source.TasksSource
import org.koin.dsl.module

val tasksListModule = module {

    single {
        TasksListViewModel(get(), get())
    }

    single {
        TasksSource(get())
    }
}

val taskDetailsModule = module {

    single {
        TaskDetailsViewModel(get())
    }
}