package com.attafitamim.app.todo.data.local.source

import com.attafitamim.app.todo.data.local.converters.LocalTaskConverter
import com.attafitamim.app.todo.data.local.db.Database
import com.attafitamim.app.todo.data.local.db.provider.DatabaseProvider
import com.attafitamim.app.todo.domain.model.Task
import com.attafitamim.app.todo.domain.repository.source.ITasksLocalDataSource

class TasksLocalDataSource(
    private val databaseProvider: DatabaseProvider
) : ITasksLocalDataSource {

    private val database by lazy {
        databaseProvider.provide(Database.NAME, Database::class.java)
    }

    override suspend fun getTasksPage(page: Int): List<Task> {
        require(page >= MIN_PAGE_NUMBER)
        val limit = PAGE_SIZE
        val offset = (page - MIN_PAGE_NUMBER) * limit
        val localTasks = database.tasksDao.getTasksPage(limit, offset)
        return localTasks.map(LocalTaskConverter::convertToTask)
    }

    override suspend fun saveTasksPage(tasks: List<Task>) {
        val localTasks = tasks.map(LocalTaskConverter::convertToLocal)
        database.tasksDao.insertTasksPage(localTasks)
    }

    override suspend fun getTask(id: Int): Task {
        val localTask = database.tasksDao.getTask(id)
        return LocalTaskConverter.convertToTask(localTask)
    }

    private companion object {
        const val PAGE_SIZE = 20
        const val MIN_PAGE_NUMBER = 1
    }
}