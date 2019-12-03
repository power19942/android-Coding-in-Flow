package com.example.architecturecomponents

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class MyBroadcastReceiver : BroadcastReceiver(){
    override fun onReceive(context: Context?, intent: Intent?) {
        if ("com.example.architecturecomponents.ACTION".equals(intent?.action)){
            var text = intent?.getStringExtra("extra")
            Toast.makeText(context,text, Toast.LENGTH_SHORT).show()
        }
    }

}