package com.attafitamim.app.todo.data.remote.client.calls

import com.attafitamim.app.todo.data.remote.client.calls.constants.RemoteParameters.PAGE
import com.attafitamim.app.todo.data.remote.client.calls.constants.RemotePaths.TASKS_PATH
import com.attafitamim.app.todo.data.remote.client.response.TasksPageResponseBody
import retrofit2.http.GET
import retrofit2.http.Query

internal interface ITasksHttpClient {

    @GET(TASKS_PATH)
    suspend fun getTasksPage(@Query(PAGE) page: Int): TasksPageResponseBody
}