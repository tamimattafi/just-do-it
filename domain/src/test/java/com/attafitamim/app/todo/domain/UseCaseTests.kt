package com.attafitamim.app.todo.domain

import com.attafitamim.app.todo.domain.model.Task
import com.attafitamim.app.todo.domain.repository.impl.TasksRepository
import com.attafitamim.app.todo.domain.repository.source.ITasksLocalDataSource
import com.attafitamim.app.todo.domain.repository.source.ITasksRemoteDataSource
import com.attafitamim.app.todo.domain.usecase.GetTask
import com.attafitamim.app.todo.domain.usecase.GetTasksPage
import com.attafitamim.app.todo.domain.utils.IConnectionManager
import org.junit.Test
import java.util.*
import kotlin.collections.ArrayList

class UseCaseTests {

    @Test
    //Checks that app will fail if we query a task before querying a tasks page and caching it
    suspend fun localTaskLoader_noCache_taskIsNull() {
        val getTasksRepository = TasksRepository(
            localDataSource,
            remoteDataSource,
            connectionManager
        )

        val getTaskUseCase = GetTask(getTasksRepository)

        val task = kotlin.runCatching {
            //Load first item of first page
            //This will throw an exception
            getTaskUseCase(20)
        }.getOrNull()

        assert(task == null)
    }

    @Test
    //Checks if data is saved properly to local storage after the first page query is done
    suspend fun localTaskLoader_hasCache_tasksAreNotNull() {
        val getTasksRepository = TasksRepository(
            localDataSource,
            remoteDataSource,
            connectionManager
        )

        val getTasksPage = GetTasksPage(getTasksRepository)
        val tasksPage = getTasksPage(1)

        val getTaskUseCase = GetTask(getTasksRepository)

        tasksPage.forEach { task ->
            val newTask = kotlin.runCatching {
                //This will never throw
                getTaskUseCase(task.id)
            }.getOrNull()

            assert(newTask != null)
        }
    }


    private val localDataSource get() = object : ITasksLocalDataSource {
        val tasks = ArrayList<Task>()
        val pageSize = 20

        override suspend fun getTasksPage(page: Int): List<Task> {
            val startIndex = (page - 1) * pageSize
            return tasks.subList(
                startIndex,
                startIndex + pageSize
            )
        }

        override suspend fun saveTasksPage(tasks: List<Task>) {
            this.tasks.addAll(tasks)
        }

        override suspend fun getTask(id: Int): Task {
            val task = tasks.find { task ->
                task.id == id
            }

            return requireNotNull(task)
        }
    }

    private val remoteDataSource get() = object : ITasksRemoteDataSource {
        val pageSize = 20

        override suspend fun getTasksPage(page: Int): List<Task> {
            val list = ArrayList<Task>()

            for (index in 0 until pageSize) {
                val task = Task(
                    id = index + (page * pageSize),
                    userId = page,
                    "Task at page $page and index $index",
                    dueDate = Date(),
                    status = Task.Status.COMPLETED
                )

                list.add(task)
            }

            return list
        }
    }

    private val connectionManager get() = object : IConnectionManager {
        override var isConnected: Boolean = false
        override fun setStateListener(listener: IConnectionManager.IStateListener) {}
        override fun removeStateListener(listener: IConnectionManager.IStateListener) {}

    }
}