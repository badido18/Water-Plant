<<<<<<< HEAD
package com.example.waterplant

import android.app.Service
import android.content.Intent
import android.os.IBinder

class AlarmService : Service() {
    var alarm = Alarm()

    override fun onCreate() {
        super.onCreate()
    }

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        alarm.setAlarm(this)
        return START_STICKY
    }

    override fun onStart(intent: Intent, startId: Int) {
        alarm.setAlarm(this)
    }

    override fun onBind(intent: Intent): IBinder? {
        return null
    }
=======
package com.example.waterplant

import android.app.Service
import android.content.Intent
import android.os.IBinder

class AlarmService : Service() {
    var alarm = Alarm()

    override fun onCreate() {
        super.onCreate()
    }

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        alarm.setAlarm(this)
        return START_STICKY
    }

    override fun onStart(intent: Intent, startId: Int) {
        alarm.setAlarm(this)
    }

    override fun onBind(intent: Intent): IBinder? {
        return null
    }
>>>>>>> 765dc99f11a86f80f2a0d44cf1feed22ba9489e9
}