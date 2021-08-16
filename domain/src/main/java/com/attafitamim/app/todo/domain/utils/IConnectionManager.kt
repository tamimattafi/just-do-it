package com.attafitamim.app.todo.domain.utils

interface IConnectionManager {
    val isConnected: Boolean

    fun setStateListener(listener: IStateListener)
    fun removeStateListener(listener: IStateListener)

    interface IStateListener {
        fun onStateChanged(isConnected: Boolean)
    }
}