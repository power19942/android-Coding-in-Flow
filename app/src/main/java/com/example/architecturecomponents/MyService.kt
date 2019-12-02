package com.example.architecturecomponents

import android.annotation.SuppressLint
import android.app.IntentService
import android.app.Notification
import android.content.Context
import android.content.Intent
import android.nfc.Tag
import android.os.Build
import android.os.PowerManager
import android.util.Log
import com.example.architecturecomponents.App.Companion.CHANNEL_ID
import kotlinx.coroutines.delay


class MyService() : IntentService("MyService") {
    companion object {
        const val TAG = "MyService"
    }

    lateinit var wakeLock: PowerManager.WakeLock

    init {
        setIntentRedelivery(false)
    }

    override fun onCreate() {
        super.onCreate()
        var powerManager = getSystemService(Context.POWER_SERVICE) as PowerManager
        wakeLock = powerManager.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "MyService:Wakelock")
        wakeLock.acquire()
        Log.d(TAG,"wakeLock.acquire()")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            var notification = Notification.Builder(this, CHANNEL_ID)
                .setContentTitle("my service")
                .setContentText("running")
                .setSmallIcon(R.drawable.ic_sentiment_satisfied_black_24dp)
                .build()
            startForeground(10, notification)
        }
    }

    override fun onHandleIntent(intent: Intent?) {
        Log.d(TAG, "handle")

        var input = intent!!.getStringExtra("extra")

        Log.d(TAG, "handle $input")

        for (i in 0..10) {
            Log.d(TAG, "loop $i")
            Thread.sleep(1000)
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        wakeLock.release()
    }


}