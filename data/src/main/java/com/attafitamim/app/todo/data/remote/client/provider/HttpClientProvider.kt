package com.attafitamim.app.todo.data.remote.client.provider

import com.attafitamim.app.todo.data.remote.client.calls.ITasksHttpClient
import com.attafitamim.app.todo.data.remote.client.calls.constants.RemotePaths
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory

class HttpClientProvider {

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(RemotePaths.BASE_URL)
            .addConverterFactory(JacksonConverterFactory.create())
            .build()
    }

    @Volatile
    private var tasksClientInstance: ITasksHttpClient? = null

    internal val tasksClient get() = synchronized(this) {
        tasksClientInstance ?: retrofit
            .create(ITasksHttpClient::class.java)
            .also(this::tasksClientInstance::set)
    }
}