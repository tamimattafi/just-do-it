package com.attafitamim.app.todo.domain.utils

interface IConnectionManager {
    val isConnected: Boolean

    fun subscribeToConnectionChange(listener: IConnectionChangeListener)
    fun unsubscribeToConnectionChange(listener: IConnectionChangeListener)

    interface IConnectionChangeListener {
        fun onConnectionStateChanged(isConnected: Boolean)
    }
}