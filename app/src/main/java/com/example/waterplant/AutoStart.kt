package com.example.waterplant


import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.example.waterplant.Alarm

class AutoStart : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        val startServiceIntent = Intent(context, AlarmService::class.java)
        context.startService(startServiceIntent)
    }

}