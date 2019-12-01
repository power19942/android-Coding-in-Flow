package com.example.architecturecomponents

import android.app.Notification
import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.os.IBinder
import androidx.core.app.NotificationCompat
import com.example.architecturecomponents.App.Companion.CHANNEL_ID

class MyService : Service() {

    override fun onCreate() {
        super.onCreate()

    }

    override fun onDestroy() {
        super.onDestroy()

    }

    // all work here run on ui thread
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        var input = intent?.getStringExtra("input")
        var notificationIntent = Intent(this, MainActivity::class.java)
        var pendingIntent = PendingIntent.getActivity(
            this, 0
            , notificationIntent, 0
        )
        var notification = NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle("My Service")
            .setContentText(input)
            .setSmallIcon(R.drawable.ic_sentiment_satisfied_24dp)
            .setContentIntent(pendingIntent)
            .build()
        startForeground(1, notification)
        //stop service after complete the work
        //stopSelf()
        return START_NOT_STICKY
    }

    override fun onBind(intent: Intent?): IBinder? = null

}