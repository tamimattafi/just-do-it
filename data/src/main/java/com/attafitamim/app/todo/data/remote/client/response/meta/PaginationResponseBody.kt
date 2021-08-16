package com.attafitamim.app.todo.data.remote.client.response.meta

import com.fasterxml.jackson.annotation.JsonProperty

internal data class PaginationResponseBody(
    @JsonProperty("total")
    val itemsCount: Int,
    @JsonProperty("pages")
    val pagesCount: Int,
    @JsonProperty("page")
    val currentPage: Int,
    @JsonProperty("limit")
    val limit: Int
)