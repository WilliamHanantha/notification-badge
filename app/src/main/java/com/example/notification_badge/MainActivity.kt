package com.example.notification_badge

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.RemoteViews
import androidx.core.app.NotificationCompat

class MainActivity : AppCompatActivity() {

    lateinit var notificationManager: NotificationManager
    lateinit var notificationChannel: NotificationChannel
    lateinit var builder: NotificationCompat.Builder
    private val channelId = "i.apps.notifications"
    private val description = "Test notification"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val btn = findViewById<Button>(R.id.btnPushNotif)

        notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager


        btn.setOnClickListener{
//            var notification = NotificationCompat.Builder(this@MainActivity, channelId)
//                .setContentTitle("New Messages")
//                .setContentText("You've received 3 new messages.")
//                .setSmallIcon(packageManager.getApplicationInfo(packageName, 0).icon)
//                .setNumber(3)
//                .build()
//
//            val notificationManager =
//                getSystemService(NOTIFICATION_SERVICE) as NotificationManager
//            notificationManager.notify(1, notification)
            val intent = Intent(this, MainActivity2::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK}
            val pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_IMMUTABLE)

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                notificationChannel = NotificationChannel(channelId, description, NotificationManager.IMPORTANCE_HIGH)
                notificationChannel.enableLights(true)
                notificationChannel.lightColor = Color.GREEN
                notificationChannel.enableVibration(true)
                notificationChannel.setShowBadge(true)
                notificationManager.createNotificationChannel(notificationChannel)

                builder = NotificationCompat.Builder(this, channelId)
                    .setSmallIcon(packageManager.getApplicationInfo(packageName, 0).icon)
                    .setLargeIcon(BitmapFactory.decodeResource(this.resources, packageManager.getApplicationInfo(packageName, 0).icon))
                    .setContentIntent(pendingIntent)
                    .setContentTitle("New Messagesyy")
                    .setContentText("prabowo mwah mwahhhh")
                    .setNumber(2)
                    .setBadgeIconType(NotificationCompat.BADGE_ICON_LARGE)
                    .setAutoCancel(true)

            } else {

                builder = NotificationCompat.Builder(this)
//                    .setContent(contentView)
                    .setSmallIcon(packageManager.getApplicationInfo(packageName, 0).icon)
                    .setLargeIcon(BitmapFactory.decodeResource(this.resources, packageManager.getApplicationInfo(packageName, 0).icon))
//                    .setContentIntent(pendingIntent)
                    .setContentTitle("New Messagesyy")
                    .setContentText("prabowo mwah mwahhhh")
            }

            notificationManager.notify(1234, builder.build())
            notificationManager.notify(1234, builder.build())
        }
    }
}