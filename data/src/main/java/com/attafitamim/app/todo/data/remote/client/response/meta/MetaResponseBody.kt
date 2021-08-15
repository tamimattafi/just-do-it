package com.attafitamim.app.todo.data.remote.client.response.meta

import com.fasterxml.jackson.annotation.JsonProperty

internal data class MetaResponseBody(
    @JsonProperty("pagination")
    val pagination: PaginationResponseBody
)