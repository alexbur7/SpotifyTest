package com.project.spotifytest

import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.getSystemService
import androidx.databinding.DataBindingUtil
import com.project.spotifytest.databinding.ActivityMainBinding
import com.spotify.android.appremote.api.ConnectionParams
import com.spotify.android.appremote.api.Connector
import com.spotify.android.appremote.api.PlayerApi
import com.spotify.android.appremote.api.SpotifyAppRemote
import com.spotify.protocol.types.Track
import java.util.*


class MainActivity : AppCompatActivity() {
    private val REQUEST_CODE = 0
    private val binding:ActivityMainBinding by lazy { DataBindingUtil.setContentView(this, R.layout.activity_main) }

    @SuppressLint("NewApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val manager:AlarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager

        binding.startButton.setOnClickListener {
            val dateAlarm:Calendar = Calendar.getInstance()
            dateAlarm.set(Calendar.HOUR_OF_DAY, binding.calendar.hour)
            dateAlarm.set(Calendar.MINUTE, binding.calendar.minute)
            val intent = Intent(applicationContext, MyReceiver::class.java)
            val pendingIntent:PendingIntent = PendingIntent.getBroadcast(this@MainActivity,REQUEST_CODE, intent, PendingIntent.FLAG_UPDATE_CURRENT)
            manager.set(AlarmManager.RTC_WAKEUP,dateAlarm.timeInMillis,pendingIntent)
            Toast.makeText(this@MainActivity,getString(R.string.alarm_start),Toast.LENGTH_SHORT).show()
        }

       // binding.stopButton.setOnClickListener { manager.cancel(pendingIntent) }

    }


}