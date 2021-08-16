package com.attafitamim.app.todo.data.local.converters

import com.attafitamim.app.todo.data.local.model.LocalTask
import com.attafitamim.app.todo.domain.model.Task
import java.util.*

internal object LocalTaskConverter {

    fun convertToLocal(task: Task): LocalTask {
        val dueTime = task.dueDate.time
        val status = task.status.name

        return LocalTask(
            task.id,
            task.userId,
            task.title,
            dueTime,
            status
        )
    }

    fun convertToTask(localTask: LocalTask): Task {
        val dueDate = Date(localTask.dueTime)
        val status = Task.Status.valueOf(localTask.status)

        return Task(
            localTask.id,
            localTask.userId,
            localTask.title,
            dueDate,
            status
        )
    }
}