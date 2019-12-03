package com.example.architecturecomponents

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    var receiver = MyBroadcastReceiver()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var filter = IntentFilter("$packageName.ACTION")
        registerReceiver(receiver, filter)

    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(receiver)
    }
}


//the other app that will trigger the receiver
// the code should be in different app
//class MainActivity : AppCompatActivity() {
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
//
//        btn.setOnClickListener {
//            var i = Intent("com.example.architecturecomponents.ACTION")
//            i.putExtra("extra", "send data")
//            sendBroadcast(i)
//        }
//    }
//
//    var Receiver = object : BroadcastReceiver() {
//        override fun onReceive(context: Context?, intent: Intent?) {
//            var text = intent?.getStringExtra("extra")
//            btn.text = text
//        }
//
//    }
//
//    override fun onStart() {
//        super.onStart()
//        registerReceiver(Receiver, IntentFilter("com.example.architecturecomponents.ACTION"))
//    }
//
//    override fun onStop() {
//        super.onStop()
//        unregisterReceiver(Receiver)
//    }
//}