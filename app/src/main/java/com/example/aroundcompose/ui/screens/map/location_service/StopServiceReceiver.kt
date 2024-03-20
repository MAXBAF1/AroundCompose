package com.example.aroundcompose.ui.screens.map.location_service

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class StopServiceReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        context.stopService(Intent(context, LocationService::class.java))
        //killProcess(myPid())
    }
}