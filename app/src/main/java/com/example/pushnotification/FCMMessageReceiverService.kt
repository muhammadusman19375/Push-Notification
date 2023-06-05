package com.example.pushnotification

import android.app.NotificationManager
import android.graphics.Color
import android.util.Log
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

//...............FMS class for receiving notification on foreground...................
class FCMMessageReceiverService : FirebaseMessagingService() {

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)
        Log.d(TAG, "onMessageReceived: Called")
        Log.d(TAG, "onMessageReceived: Message received from: ${message.from}")

        /* Check it is data message or notification because data message not contain notification
        (message.notification != null) tells notification is not empty
         (message.data.isNotEmpty()) tells it data message not empty*/
        if (message.notification != null) {
            val notificationTitle = message.notification!!.title
            val notificationBody = message.notification!!.body
            createNewNotification(notificationTitle!!, notificationBody!!)
        }

        if(message.data.isNotEmpty()){
            Log.d(TAG, "onMessageReceived: Data size: ${message.data.size}")

            for(key in message.data.keys){
                Log.d(TAG, "onMessageReceived Key: $key ,Data: ${message.data.get(key)}")
            }

            Log.d(TAG, "onMessageReceived: Data ${message.data}")
        }
    }

    override fun onDeletedMessages() {
        super.onDeletedMessages()
        Log.d(TAG, "onDeletedMessages: Called")
    }

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        Log.d(TAG, "onNewToken: Called")
    }

    private fun createNewNotification(title: String, body: String) {
        val notification = NotificationCompat.Builder(this, FMC_CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_chat)
            .setContentTitle(title)
            .setContentText(body)
            .build()

        val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(1002, notification)
    }
}