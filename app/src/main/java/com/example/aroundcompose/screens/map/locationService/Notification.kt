package com.example.aroundcompose.screens.map.locationService

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import androidx.core.app.NotificationCompat
import com.example.aroundcompose.MainActivity
import com.example.aroundcompose.R

fun startNotification(service: Service) {
    val nChannel = NotificationChannel(
        LocationService.CHANNEL_ID, "Location Service", NotificationManager.IMPORTANCE_DEFAULT
    )
    val nManager = service.getSystemService(NotificationManager::class.java) as NotificationManager
    nManager.createNotificationChannel(nChannel)


    val nIntent = Intent(service, MainActivity::class.java)
    val pIntent = PendingIntent.getActivity(
        service, 10, nIntent, PendingIntent.FLAG_IMMUTABLE
    )

    val stopIntent = Intent(service, StopServiceReceiver::class.java)
    val stopAction = PendingIntent.getBroadcast(
        service, 0, stopIntent, PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
    )

    val notification = NotificationCompat.Builder(service, LocationService.CHANNEL_ID)
        .setSmallIcon(R.mipmap.ic_launcher)
        .setContentTitle(service.getString(R.string.around_running))
        .addAction(R.mipmap.ic_launcher, service.getString(R.string.stop_and_exit), stopAction)
        .setContentIntent(pIntent).setAutoCancel(true).build()

    service.startForeground(LocationService.NOTIFICATION_ID, notification)
}
