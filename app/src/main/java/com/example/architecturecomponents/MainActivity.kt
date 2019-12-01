package com.example.architecturecomponents

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        var intent = Intent(this@MainActivity,MyService::class.java)
        btn_start.setOnClickListener {
            intent.putExtra("input",edit_text.text.toString())
            ContextCompat.startForegroundService(this,intent)
        }



        btn_stop.setOnClickListener {
            stopService(intent)
        }
    }
}
