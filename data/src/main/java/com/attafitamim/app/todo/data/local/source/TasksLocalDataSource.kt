package com.attafitamim.app.todo.data.local.source

import com.attafitamim.app.todo.data.local.converters.TasksConverter
import com.attafitamim.app.todo.data.local.db.Database
import com.attafitamim.app.todo.domain.model.Task
import com.attafitamim.app.todo.domain.repository.source.ITasksLocalDataSource

class TasksLocalDataSource(private val database: Database) : ITasksLocalDataSource {

    override suspend fun getTasksPage(page: Int): List<Task> {
        require(page >= MIN_PAGE_NUMBER)
        val limit = PAGE_SIZE
        val offset = (page - MIN_PAGE_NUMBER) * limit
        val localTasks = database.tasksDao.getTasksPage(limit, offset)
        return localTasks.map(TasksConverter::convertToTask)
    }

    override suspend fun saveTasksPage(tasks: List<Task>) {
        val localTasks = tasks.map(TasksConverter::convertToLocal)
        database.tasksDao.insertTasksPage(localTasks)
    }

    override suspend fun getTask(id: Int): Task {
        val localTask = database.tasksDao.getTask(id)
        return TasksConverter.convertToTask(localTask)
    }

    private companion object {
        const val PAGE_SIZE = 20
        const val MIN_PAGE_NUMBER = 1
    }
}