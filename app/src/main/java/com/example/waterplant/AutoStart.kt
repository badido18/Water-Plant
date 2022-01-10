<<<<<<< HEAD
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

=======
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

>>>>>>> 765dc99f11a86f80f2a0d44cf1feed22ba9489e9
}