package com.attafitamim.app.todo.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.attafitamim.app.todo.data.local.db.LocalTables

@Entity(tableName = LocalTables.TASKS_TABLE)
internal data class LocalTask(
    @PrimaryKey val id: Int,
    val userId: Int,
    val title: String,
    val dueTime: Long,
    val status: String
)