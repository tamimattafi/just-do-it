package com.attafitamim.app.todo.domain.repository.source

import com.attafitamim.app.todo.domain.model.Task

interface ITasksRemoteDataSource {
    suspend fun getTasksPage(page: Int): List<Task>
}