package com.attafitamim.app.todo.domain.repository.source

import com.attafitamim.app.todo.domain.model.Task

interface ITasksLocalDataSource {
    suspend fun getTasksPage(page: Int): List<Task>
    suspend fun saveTasksPage(tasks: List<Task>)
    suspend fun getTask(id: Int): Task
}