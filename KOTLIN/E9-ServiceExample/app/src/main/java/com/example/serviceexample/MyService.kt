package com.example.serviceexample

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async

class MyService : Service() {

    private val TAG = "ServiceExample"
    private val coroutineScope = CoroutineScope(Dispatchers.IO)

    override fun onCreate() {
        Log.i(TAG, "Service onCreate")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        coroutineScope.async {
            Log.i(TAG, "Service onStartCommand " + startId)
            var i: Int = 0
            while (i <= 3) {
                try {
                    Thread.sleep(10000)
                    i++
                } catch (e: Exception) {
                }
                Log.i(TAG, "Service running")
            }
        }
        return Service.START_STICKY
    }

    override fun onBind(intent: Intent): IBinder {
        Log.i(TAG, "Service onBind")
        TODO("Return the communication channel to the service.")
    }

    override fun onDestroy() {
        Log.i(TAG, "Service onDestroy")
    }

}
