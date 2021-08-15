package com.attafitamim.app.todo.domain.repository.impl

import com.attafitamim.app.todo.domain.model.Task
import com.attafitamim.app.todo.domain.repository.ITasksRepository
import com.attafitamim.app.todo.domain.repository.source.TasksLocalDataSource
import com.attafitamim.app.todo.domain.repository.source.TasksRemoteDataSource
import com.attafitamim.app.todo.domain.utils.IConnectionManager

class TasksRepository(
    private val localDataSource: TasksLocalDataSource,
    private val remoteDataSource: TasksRemoteDataSource,
    private val connectionManager: IConnectionManager
) : ITasksRepository {

    override suspend fun getTasksPage(page: Int) =
        if (connectionManager.isConnected) this.syncAndLoadTasksPage(page)
        else localDataSource.getTasksPage(page)

    override suspend fun getTask(id: Int) = localDataSource.getTask(id)

    private suspend fun syncAndLoadTasksPage(page: Int): List<Task> {
        val tasksPage = remoteDataSource.getTasksPage(page)
        localDataSource.saveTasksPage(tasksPage)
        return tasksPage
    }
}