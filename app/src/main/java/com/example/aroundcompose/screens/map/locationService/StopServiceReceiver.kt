package com.example.aroundcompose.screens.map.locationService

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Process.killProcess
import android.os.Process.myPid

class StopServiceReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        context.stopService(Intent(context, LocationService::class.java))
        //killProcess(myPid())
    }
}