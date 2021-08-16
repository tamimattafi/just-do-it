package com.attafitamim.app.todo.data.local.db.provider

import androidx.room.RoomDatabase

abstract class DatabaseProvider {

    private val databases = HashMap<String, RoomDatabase>()

    protected abstract fun <T : RoomDatabase> createDatabase(name: String, clazz: Class<T>): T

    internal fun <T : RoomDatabase> provide(
        name: String,
        clazz: Class<T>
    ): T = synchronized(this) {
        this.getDatabaseInstance(name) ?: this.createDatabase(name, clazz).also { database ->
            databases[name] = database
        }
    }

    private fun <T : RoomDatabase> getDatabaseInstance(name: String): T?
        = databases[name] as? T
}