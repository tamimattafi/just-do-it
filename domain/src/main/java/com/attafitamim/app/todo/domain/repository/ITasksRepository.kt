package com.attafitamim.app.todo.domain.repository

import com.attafitamim.app.todo.domain.model.Task

interface ITasksRepository {
    suspend fun getTasksPage(page: Int): List<Task>
    suspend fun getTask(id: Int): Task
}