package com.example.architecturecomponents

import android.app.job.JobParameters
import android.app.job.JobService
import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.annotation.UiThread
import kotlinx.coroutines.*

@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
class MyJobService : JobService() {
    companion object {
        const val TAG = "MyJobService"
    }

    var canceled = false

    override fun onStartJob(params: JobParameters?): Boolean {
        Log.d(TAG, "job started")

        GlobalScope.launch {
            for (i in 0..5) {
                if (canceled)
                    return@launch
                Log.d(TAG, "job Run: $i")
                delay(1000)
            }
            withContext(Dispatchers.Main){
                Toast.makeText(this@MyJobService,"finished",Toast.LENGTH_SHORT).show()
            }
            jobFinished(params, false/* reschedule the service*/)
        }

        return true
    }

    override fun onStopJob(params: JobParameters?): Boolean {
        Log.d(TAG, "job canceled")
        canceled = true
        // reschedule the service
        return true
    }


}