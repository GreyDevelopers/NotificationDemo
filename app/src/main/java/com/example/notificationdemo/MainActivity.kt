package com.example.notificationdemo

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.notificationdemo.Constansts.CHANNEL_ID
import com.example.notificationdemo.Constansts.CHANNEL_NAME
import com.example.notificationdemo.Constansts.NOTIFICATION_ID

object Constansts{
    const val CHANNEL_ID = "channedId"
    const val CHANNEL_NAME = "channelName"
    const val NOTIFICATION_ID = 0
}

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val btn = findViewById<Button>(R.id.btn)
        val edName = findViewById<EditText>(R.id.edName)
        val edJob = findViewById<EditText>(R.id.edJob)

        createNotificationChannel()
        val intent = Intent(this,MainActivity::class.java)
        btn.setOnClickListener {
            val pendingIntent = PendingIntent.getActivity(this,0,intent,0)
            val builder = NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle(edName.text.toString())
                .setContentText(edJob.text.toString())
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
            with(NotificationManagerCompat.from(this)){
                notify(NOTIFICATION_ID,builder.build())
                System.exit(0)
            }
        }


    }

    private fun createNotificationChannel() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val channel = NotificationChannel(CHANNEL_ID, CHANNEL_NAME,NotificationManager.IMPORTANCE_DEFAULT)
            val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }
}