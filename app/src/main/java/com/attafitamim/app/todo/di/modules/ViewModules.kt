package com.attafitamim.app.todo.di.modules

import com.attafitamim.app.todo.view.tasks.details.model.TaskDetailsViewModel
import com.attafitamim.app.todo.view.tasks.list.model.TasksListViewModel
import com.attafitamim.app.todo.view.tasks.list.source.TasksSource
import org.koin.dsl.module

val tasksListModule = module {

    factory {
        TasksListViewModel(get(), get())
    }

    factory {
        TasksSource(get())
    }
}

val taskDetailsModule = module {

    factory { parameters ->
        TaskDetailsViewModel(get(), parameters.get())
    }
}