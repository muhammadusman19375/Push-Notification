package com.example.pushnotification

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.graphics.Color
import android.os.Build

class PushNotificationApp : Application() {

    override fun onCreate() {
        super.onCreate()
        //......................Create Notification Channel..................................
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val fmcChannel = NotificationChannel(FMC_CHANNEL_ID, "FMC_Channel", NotificationManager.IMPORTANCE_HIGH)
            fmcChannel.enableLights(true)
            fmcChannel.enableVibration(true)
            fmcChannel.lightColor = Color.RED
            fmcChannel.vibrationPattern = longArrayOf(100, 200, 300, 400, 500, 400, 300, 200, 400)
            val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(fmcChannel)
        }

    }

}