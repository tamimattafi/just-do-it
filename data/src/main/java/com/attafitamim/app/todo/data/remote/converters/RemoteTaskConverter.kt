package com.attafitamim.app.todo.data.remote.converters

import com.attafitamim.app.todo.data.remote.model.RemoteTask
import com.attafitamim.app.todo.domain.model.Task
import java.text.SimpleDateFormat
import java.util.*

internal object RemoteTaskConverter {

    fun convertToTask(remoteTask: RemoteTask): Task {
        val dataFormat = SimpleDateFormat(RemoteTask.TIME_PATTERN, Locale.ROOT)
        val dueDate = dataFormat.parse(remoteTask.dueDate).let(::requireNotNull)

        val status = this.convertToStatus(remoteTask.status)

        return Task(
            remoteTask.id,
            remoteTask.userId,
            remoteTask.title,
            dueDate,
            status
        )
    }

    private fun convertToStatus(remoteStatus: RemoteTask.Status) = when (remoteStatus) {
        RemoteTask.Status.PENDING -> Task.Status.PENDING
        RemoteTask.Status.COMPLETED -> Task.Status.COMPLETED
    }
}