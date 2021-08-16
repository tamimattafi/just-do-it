package com.attafitamim.app.todo.view.tasks.details.widget

import androidx.compose.foundation.layout.Column
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.res.stringResource
import com.attafitamim.app.todo.domain.model.Task
import com.attafitamim.app.todo.view.R
import com.attafitamim.app.todo.view.common.styles.DateFormats
import com.attafitamim.app.todo.view.common.styles.DefaultPadding
import com.attafitamim.app.todo.view.tasks.details.model.TaskDetailsViewModel
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun TaskDetails(viewModel: TaskDetailsViewModel) {
    val state = viewModel.container.stateFlow.collectAsState().value
    TaskItem(task = state.task)
}

@Composable
private fun TaskItem(task: Task?) {
    if (task == null) {
        val padding = DefaultPadding()
        CircularProgressIndicator(modifier = padding)
        return
    }

    val title = stringResource(id = R.string.task_list_label, task.id, task.title)
    val padding = DefaultPadding()

    val dateFormat = SimpleDateFormat(DateFormats.DEFAULT_DATE_FORMAT, Locale.getDefault())
    val date = dateFormat.format(task.dueDate)

    Column {
        Text(text = title, modifier = padding)
        Text(text = date, modifier = padding)
    }
}