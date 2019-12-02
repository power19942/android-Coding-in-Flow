package com.example.architecturecomponents

import android.app.job.JobInfo
import android.app.job.JobScheduler
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val jobId = 3321



        btn_start.setOnClickListener {
            var i = Intent(this,MyJobService::class.java)
            i.putExtra("extra",edit_text_input.text.toString())
            MyJobService.enqueue(this@MainActivity,i)
        }


    }
}
