package com.example.alarmrepeattest

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.SystemClock
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController


class MainActivity : AppCompatActivity() {
    val requestId = 12345

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupActionBarWithNavController(findNavController(R.id.main_fragment))
        //alarmSetup()
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController : NavController = findNavController(R.id.main_fragment)
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

    fun alarmSetup() {
        val repeatIntervalMillis = 60000L
        val alarmIntent = Intent(applicationContext, AlarmReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(
            applicationContext, requestId, alarmIntent, PendingIntent.FLAG_UPDATE_CURRENT
        )
        val alarmManager = this.getSystemService(ALARM_SERVICE) as? AlarmManager
        //SystemClock.elapsedRealtime() + AlarmManager.INTERVAL_HALF_HOUR,
        //AlarmManager.INTERVAL_FIFTEEN_MINUTES
        if (alarmManager != null && pendingIntent != null) {
            alarmManager.setInexactRepeating(
                AlarmManager.ELAPSED_REALTIME_WAKEUP,
                SystemClock.elapsedRealtime(),
                repeatIntervalMillis, pendingIntent
            )
        }

    }
}