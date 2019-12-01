package com.example.architecturecomponents

import android.app.job.JobInfo
import android.app.job.JobScheduler
import android.content.ComponentName
import android.content.Context
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val jobId = 3321



        btn_start.setOnClickListener {

            var componentName = ComponentName(this, MyJobService::class.java)
            var info = JobInfo.Builder(jobId, componentName)
                .setRequiresCharging(true)
                .setRequiredNetworkType(JobInfo.NETWORK_TYPE_UNMETERED)
                .setPersisted(true)
                .setPeriodic(15 * 60 * 1000)
                .build()

            var scheduler = getSystemService(Context.JOB_SCHEDULER_SERVICE) as JobScheduler

            var res = scheduler.schedule(info)

            if (res == JobScheduler.RESULT_SUCCESS) {
                Log.d("MyJobService", "job completed")
            } else
                Log.d("MyJobService", "job error")

        }



        cancel.setOnClickListener {
            var scheduler = getSystemService(Context.JOB_SCHEDULER_SERVICE) as JobScheduler
            scheduler.cancel(jobId)
            Log.d("MyJobService", "job canceled")
        }
    }
}
