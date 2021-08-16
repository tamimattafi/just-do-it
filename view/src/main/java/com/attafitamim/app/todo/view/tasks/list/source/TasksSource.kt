package com.attafitamim.app.todo.view.tasks.list.source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.attafitamim.app.todo.domain.model.Task
import com.attafitamim.app.todo.domain.usecase.GetTasksPage

class TasksSource(
    private val getTasksPage: GetTasksPage
) : PagingSource<Int, Task>() {

    override fun getRefreshKey(state: PagingState<Int, Task>) = START_PAGE_NUMBER

    override suspend fun load(params: LoadParams<Int>) =
        try {
            val currentPage = params.key ?: START_PAGE_NUMBER
            val tasksList = getTasksPage(currentPage)

            val previousPage = if (currentPage == START_PAGE_NUMBER) null
            else currentPage - DEFAULT_PAGE_STEP

            val nextPage = currentPage + DEFAULT_PAGE_STEP

            LoadResult.Page(
                data = tasksList,
                prevKey = previousPage,
                nextKey = nextPage.plus(DEFAULT_PAGE_STEP)
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }

    private companion object {
        const val START_PAGE_NUMBER = 1
        const val DEFAULT_PAGE_STEP = 1
    }
}