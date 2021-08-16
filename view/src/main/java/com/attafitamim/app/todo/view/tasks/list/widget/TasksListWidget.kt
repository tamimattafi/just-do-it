package com.attafitamim.app.todo.view.tasks.list.widget

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.attafitamim.app.todo.domain.model.Task
import com.attafitamim.app.todo.view.R
import com.attafitamim.app.todo.view.common.styles.DefaultPadding
import com.attafitamim.app.todo.view.common.widgets.StateItem
import com.attafitamim.app.todo.view.main.navigation.NavigationScreens
import com.attafitamim.app.todo.view.main.navigation.navigate
import com.attafitamim.app.todo.view.tasks.list.model.TasksListSideEffect
import com.attafitamim.app.todo.view.tasks.list.model.TasksListViewModel
import kotlinx.coroutines.flow.collectLatest

@Composable
fun TasksList(viewModel: TasksListViewModel, navController: NavController) {
    val state = viewModel.container.stateFlow.collectAsState().value

    val lazyTasks = state.tasksFlow?.collectAsLazyPagingItems()
    Column {
        ConnectionLabel(isConnected = state.isConnected)
        TasksColumn(lazyTasks = lazyTasks, viewModel::openTaskDetails)
    }

    LaunchedEffect(viewModel) {
        viewModel.container.sideEffectFlow.collectLatest { sideEffect ->
            when (sideEffect) {
                is TasksListSideEffect.OpenTaskDetails -> navController.navigate(
                    screen = NavigationScreens.TASK_DETAILS,
                    sideEffect.taskId
                )
            }
        }
    }
}

@Composable
private inline fun TasksColumn(
    lazyTasks: LazyPagingItems<Task>?,
    crossinline onTaskClick: (Task) -> Unit
) {
    LazyColumn {
        if (lazyTasks != null) items(items = lazyTasks, key = Task::id) { task ->
            requireNotNull(task)
            TaskItem(task = task) {
                onTaskClick.invoke(task)
            }
        }

        StateItem(lazyItems = lazyTasks)
    }
}

@Composable
private fun ConnectionLabel(isConnected: Boolean) {
    val connectionStateId = if (isConnected) R.string.connection_state_online_label
    else R.string.connection_state_offline_label

    val connectionState = stringResource(id = connectionStateId)
    val title = stringResource(id = R.string.connection_state_label, connectionState)

    val padding = DefaultPadding()
    Text(text = title, modifier = padding)
}

@Composable
private fun TaskItem(task: Task, onTaskClick: () -> Unit) {
    val title = stringResource(id = R.string.task_list_label, task.id, task.title)
    val padding = DefaultPadding()

    TextButton(onClick = onTaskClick) {
        Text(text = title, modifier = padding)
    }
}