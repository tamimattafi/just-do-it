package com.attafitamim.app.todo.domain.usecase

import com.attafitamim.app.todo.domain.repository.ITasksRepository

class GetTasksPage(private val tasksRepository: ITasksRepository) {
    suspend operator fun invoke(page: Int) = tasksRepository.getTasksPage(page)
}