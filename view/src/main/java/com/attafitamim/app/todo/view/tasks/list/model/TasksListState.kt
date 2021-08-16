package com.attafitamim.app.todo.view.tasks.list.model

import androidx.paging.PagingData
import com.attafitamim.app.todo.domain.model.Task
import kotlinx.coroutines.flow.Flow

data class TasksListState(
    val tasksFlow: Flow<PagingData<Task>>? = null,
    val isConnected: Boolean = false
)