package com.attafitamim.app.todo.data.remote.client.response

import com.attafitamim.app.todo.data.remote.client.response.meta.MetaResponseBody
import com.attafitamim.app.todo.data.remote.model.RemoteTask
import com.fasterxml.jackson.annotation.JsonProperty

internal data class TasksPageResponseBody(
    @JsonProperty("code")
    val code: Int,
    @JsonProperty("meta")
    val meta: MetaResponseBody,
    @JsonProperty("data")
    val tasks: List<RemoteTask>
)