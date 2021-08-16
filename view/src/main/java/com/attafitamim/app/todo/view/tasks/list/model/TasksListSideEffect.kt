package com.attafitamim.app.todo.view.tasks.list.model

sealed class TasksListSideEffect {
    class OpenTaskDetails(val taskId: Int) : TasksListSideEffect()
}