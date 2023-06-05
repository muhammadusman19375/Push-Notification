package com.example.pushnotification

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.pushnotification.databinding.ActivityPracticeBinding

class PracticeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPracticeBinding
    private lateinit var notificationChannel: NotificationChannel
    private lateinit var notificationManager: NotificationManager
    private lateinit var notificationBuilder: Notification.Builder
    private val channelId = "ChannelId"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPracticeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViews()
    }

    private fun initViews() {
        setListeners()
    }

    private fun setListeners() {
        binding.btnSimpleNotification.setOnClickListener {
            makeSimpleNotification()
        }
        binding.btnBigPictureNotification.setOnClickListener {
            makeBigPictureNotification()
        }
        binding.btnInboxStyleNotification.setOnClickListener {
            makeInboxStyleNotification()
        }
    }

    private fun makeSimpleNotification() {
        getNotificationChannel()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationBuilder = Notification.Builder(this@PracticeActivity, channelId)
                .setContentTitle("SimpleNotificationTitle")
                .setContentText("SimpleNotificationText")
                .setSmallIcon(R.drawable.ic_chat)
                .setContentIntent(setPendingIntent(this@PracticeActivity))
            notificationManager.notify(1, notificationBuilder.build())
        }
    }

    private fun makeInboxStyleNotification() {
        getNotificationChannel()
        val style = Notification.InboxStyle()
        style.apply {
            addLine("Message 1  hey")
            addLine("Message 2  Hi")
            addLine("Message 3  how are you?")
            addLine("Message 4  I am good")
            addLine("Message 5  you are bad")
            addLine("Message 6  okay")
            addLine("Message 7  fine")
            addLine("Message 8  let's go")
            setSummaryText("+10 more items..")
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationBuilder = Notification.Builder(this@PracticeActivity, channelId)
                .setContentTitle("InboxStyleNotificationTitle")
                .setContentText("InboxStyleNotificationText")
                .setSmallIcon(R.drawable.ic_chat)
                .setStyle(style)
                .setContentIntent(setPendingIntent(this@PracticeActivity))
            notificationManager.notify(1, notificationBuilder.build())
        }
    }

    private fun makeBigPictureNotification() {
        getNotificationChannel()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            val bitmap = BitmapFactory.decodeResource(resources, R.drawable.ideas)
            val style = Notification.BigPictureStyle().bigPicture(bitmap)

            notificationBuilder = Notification.Builder(this@PracticeActivity, channelId)
                .setContentTitle("BigPictureNotificationTitle")
                .setContentText("BigPictureNotificationText")
                .setSmallIcon(R.drawable.ic_chat)
                .setStyle(style)
                .setContentIntent(setPendingIntent(this@PracticeActivity))
            notificationManager.notify(1, notificationBuilder.build())
        }
    }

    private fun getNotificationChannel() {
        notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationChannel = NotificationChannel(
                channelId,
                "Test",
                NotificationManager.IMPORTANCE_HIGH
            ).also {
                it.enableLights(true)
                it.enableVibration(true)
            }
            notificationManager.createNotificationChannel(notificationChannel)
        }
    }

    private fun setPendingIntent(context: Context): PendingIntent {
        val intent = Intent(this@PracticeActivity, MainActivity::class.java)
        return PendingIntent.getActivities(
            context,
            0,
            arrayOf(intent),
            PendingIntent.FLAG_IMMUTABLE
        )
    }
}