package com.attafitamim.app.todo.data.remote.client.calls.provider

import com.attafitamim.app.todo.data.remote.client.calls.ITasksHttpClient
import com.attafitamim.app.todo.data.remote.client.calls.constants.RemotePaths
import retrofit2.Retrofit

class HttpClientProvider {

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(RemotePaths.BASE_URL)
            .build()
    }

    @Volatile
    private var tasksClient: ITasksHttpClient? = null

    internal fun provideTasksClient(): ITasksHttpClient = synchronized(this) {
        tasksClient ?: retrofit
            .create(ITasksHttpClient::class.java)
            .also(this::tasksClient::set)
    }
}