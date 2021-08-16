package com.attafitamim.app.todo.domain.model

import java.util.*

data class Task(
    val id: Int,
    val userId: Int,
    val title: String,
    val dueDate: Date,
    val status: Status
) {

    enum class Status {
        PENDING,
        COMPLETED
    }
}