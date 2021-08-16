package com.attafitamim.app.todo.view.tasks.details.model

import androidx.lifecycle.ViewModel
import com.attafitamim.app.todo.domain.usecase.GetTask
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class TaskDetailsViewModel(
    val getTask: GetTask
): ViewModel(), ContainerHost<TaskDetailsState, Unit> {

    override val container by lazy {
        val initialState = TaskDetailsState()
        container<TaskDetailsState, Unit>(initialState)
    }

    fun prepare(taskId: Int) = intent {
        val task = getTask(taskId)

        reduce {
            state.copy(task = task)
        }
    }
}