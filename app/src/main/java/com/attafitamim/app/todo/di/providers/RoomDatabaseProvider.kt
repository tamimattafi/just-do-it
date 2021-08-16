package com.attafitamim.app.todo.di.providers

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.attafitamim.app.todo.data.local.db.provider.DatabaseProvider

class RoomDatabaseProvider(private val context: Context) : DatabaseProvider() {

    override fun <T : RoomDatabase> createDatabase(name: String, clazz: Class<T>): T =
        Room.databaseBuilder(context, clazz, name).build()
}