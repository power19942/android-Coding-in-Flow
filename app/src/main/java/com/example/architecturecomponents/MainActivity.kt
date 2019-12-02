package com.example.architecturecomponents

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //intent service do the work on background thread

        btn_start.setOnClickListener {
            var i = Intent(this,MyService::class.java)
            i.putExtra("extra",txt.text.toString())
            ContextCompat.startForegroundService(this@MainActivity,i)
        }

    }
}
