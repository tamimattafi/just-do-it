package com.attafitamim.app.todo.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.attafitamim.app.todo.data.local.db.Database.Companion.EXPORT_SCHEME
import com.attafitamim.app.todo.data.local.db.Database.Companion.VERSION
import com.attafitamim.app.todo.data.local.db.dao.LocalTasksDao
import com.attafitamim.app.todo.data.local.model.LocalTask

@Database(
    entities = [LocalTask::class],
    version = VERSION,
    exportSchema = EXPORT_SCHEME
)
abstract class Database : RoomDatabase() {
    internal abstract val tasksDao: LocalTasksDao

    internal companion object {
        const val VERSION = 1
        const val EXPORT_SCHEME = false
    }
}