package com.attafitamim.app.todo.data.remote.source

import com.attafitamim.app.todo.data.remote.client.provider.HttpClientProvider
import com.attafitamim.app.todo.data.remote.converters.RemoteTaskConverter
import com.attafitamim.app.todo.domain.model.Task
import com.attafitamim.app.todo.domain.repository.source.ITasksRemoteDataSource

class TasksRemoteDataSource(private val clientProvider: HttpClientProvider)
    : ITasksRemoteDataSource {

    override suspend fun getTasksPage(page: Int): List<Task> {
        val tasksPage = clientProvider.tasksClient.getTasksPage(page)
        return tasksPage.tasks.map(RemoteTaskConverter::convertToTask)
    }
}