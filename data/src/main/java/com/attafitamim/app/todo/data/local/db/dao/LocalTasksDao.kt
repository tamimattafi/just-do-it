package com.attafitamim.app.todo.data.local.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.attafitamim.app.todo.data.local.db.LocalTables.TASKS_TABLE
import com.attafitamim.app.todo.data.local.model.LocalTask

@Dao
internal interface LocalTasksDao {

    @Query("SELECT * FROM $TASKS_TABLE WHERE id = :id")
    suspend fun getTask(id: Int): LocalTask

    @Query("SELECT * FROM $TASKS_TABLE LIMIT :limit OFFSET :offset")
    suspend fun getTasksPage(limit: Int, offset: Int): List<LocalTask>

    @Insert
    suspend fun insertTasksPage(page: List<LocalTask>)
}