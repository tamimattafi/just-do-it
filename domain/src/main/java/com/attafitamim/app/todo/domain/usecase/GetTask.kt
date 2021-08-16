package com.attafitamim.app.todo.domain.usecase

import com.attafitamim.app.todo.domain.repository.ITasksRepository

class GetTask(private val tasksRepository: ITasksRepository) {
    suspend operator fun invoke(id: Int) = tasksRepository.getTask(id)
}