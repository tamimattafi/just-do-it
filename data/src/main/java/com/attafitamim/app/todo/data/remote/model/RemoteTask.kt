package com.attafitamim.app.todo.data.remote.model

import com.fasterxml.jackson.annotation.JsonProperty

internal data class RemoteTask(
    @JsonProperty("id")
    val id: Int,
    @JsonProperty("user_id")
    val userId: Int,
    @JsonProperty("title")
    val title: String,
    @JsonProperty("due_on")
    val dueDate: String,
    @JsonProperty("status")
    val status: Status
) {

    companion object {
       const val TIME_PATTERN = "yyyy-MM-dd'T'HH:mm:ss.SSSZ"
    }

    enum class Status {
        @JsonProperty("pending")
        PENDING,
        @JsonProperty("completed")
        COMPLETED
    }
}