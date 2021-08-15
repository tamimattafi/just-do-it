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
internal abstract class Database : RoomDatabase() {
    abstract val tasksDao: LocalTasksDao

    companion object {
        const val NAME = "main-db"
        const val VERSION = 1
        const val EXPORT_SCHEME = false
    }
}