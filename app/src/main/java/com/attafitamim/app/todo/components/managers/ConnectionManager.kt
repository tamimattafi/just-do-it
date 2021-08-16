package com.attafitamim.app.todo.components.managers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.attafitamim.app.todo.domain.utils.IConnectionManager

import android.net.ConnectivityManager
import android.content.IntentFilter

class ConnectionManager(private val context: Context) : IConnectionManager {

    override val isConnected: Boolean get() {
        val activeNetworkInfo = connectivityManager.activeNetworkInfo
        return activeNetworkInfo != null && activeNetworkInfo.isConnected
    }

    private val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    private var broadcastReceiver: BroadcastReceiver? = null

    override fun setStateListener(listener: IConnectionManager.IStateListener) {
        val receiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context, intent: Intent) {
                listener.onStateChanged(isConnected)
            }
        }

        val filter = IntentFilter(CONNECTION_CHANGE_ACTION)
        context.registerReceiver(receiver, filter)

        this.broadcastReceiver = receiver
    }

    override fun removeStateListener(listener: IConnectionManager.IStateListener) {
        this.broadcastReceiver?.let(context::unregisterReceiver)
        this.broadcastReceiver = null
    }

    private companion object {
        const val CONNECTION_CHANGE_ACTION = "android.net.conn.CONNECTIVITY_CHANGE"
    }
}