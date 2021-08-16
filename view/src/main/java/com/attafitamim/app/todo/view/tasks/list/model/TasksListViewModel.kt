package com.attafitamim.app.todo.view.tasks.list.model

import androidx.lifecycle.ViewModel
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import com.attafitamim.app.todo.domain.model.Task
import com.attafitamim.app.todo.domain.utils.IConnectionManager
import com.attafitamim.app.todo.view.tasks.list.source.TasksSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class TasksListViewModel(
    private val tasksSource: TasksSource,
    private val connectionManger: IConnectionManager
) : ViewModel(), ContainerHost<TasksListState, TasksListSideEffect>,
    IConnectionManager.IStateListener {

    override val container by lazy {
        val initialState = TasksListState()
        container<TasksListState, TasksListSideEffect>(initialState)
    }

    init {
        connectionManger.setStateListener(this)
        this.loadData()
    }

    override fun onStateChanged(isConnected: Boolean) = intent {
        reduce {
            state.copy(isConnected = isConnected)
        }

        loadData()
    }

    override fun onCleared() {
        connectionManger.removeStateListener(this)
        super.onCleared()
    }

    fun openTaskDetails(task: Task) = intent {
        val openTaskDetails = TasksListSideEffect.OpenTaskDetails(task.id)
        postSideEffect(openTaskDetails)
    }

    private fun loadData() = intent {
        val pagingConfig = PagingConfig(pageSize = DEFAULT_DISPLAY_ITEMS_SIZE)

        val tasksFlow = Pager(pagingConfig) { tasksSource }.flow
        reduce {
            state.copy(tasksFlow = tasksFlow)
        }
    }

    private companion object  {
        const val DEFAULT_DISPLAY_ITEMS_SIZE = 20
    }
}