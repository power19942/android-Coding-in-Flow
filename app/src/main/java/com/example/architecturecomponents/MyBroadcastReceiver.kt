package com.example.architecturecomponents

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class MyBroadcastReceiver : BroadcastReceiver(){
    override fun onReceive(context: Context?, intent: Intent?) {
        Toast.makeText(context,"sdf;a,mdlaksmdal;smd", Toast.LENGTH_SHORT).show()
    }

}