package com.example.architecturecomponents

import android.app.job.JobParameters
import android.app.job.JobService
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.annotation.UiThread
import androidx.core.app.JobIntentService
import kotlinx.coroutines.*

@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
class MyJobService : JobIntentService() {
    companion object {
        const val TAG = "MyJobService"

        fun enqueue(contenxt:Context,work:Intent){
            enqueueWork(contenxt,MyJobService::class.java,321,work)
        }
    }

    override fun onCreate() {
        super.onCreate()
        Log.d(TAG,"oncreate")
    }

    override fun onHandleWork(intent: Intent) {
        var input = intent!!.getStringExtra("extra")

        Log.d(TAG, "handle $input")

        for (i in 0..10) {
            Log.d(TAG, "loop $i")
            if (isStopped)
                return
            Thread.sleep(1000)
        }
    }

    //run when job stop
    override fun onStopCurrentWork(): Boolean {
        // resume the work : default value true
        return super.onStopCurrentWork()
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG,"onDestroy")
    }


}