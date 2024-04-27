package com.example.notificationpractice

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.getSystemService
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    val CHANNEL_ID ="Null"
    val CHANNEL_NAME = "Null"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val btn = findViewById<Button>(R.id.Button)

        //Creating Pending Intent
        val intent = Intent(this,MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_MUTABLE)

        //called Channel
        createNotificationChannel()


        //Builder For Modification of Notification
        val builder = NotificationCompat.Builder(this,CHANNEL_ID)
            .setContentTitle("it's Working")
            .setContentText("Click Here")
            .setSmallIcon(R.drawable.baseline_notification_important_24)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setContentIntent(pendingIntent)
            .build()

        val nManagerCompact = NotificationManagerCompat.from(this)


        btn.setOnClickListener {
            if (ActivityCompat.checkSelfPermission(
                    this,
                    Manifest.permission.POST_NOTIFICATIONS
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                Toast.makeText(this,"Give Permission", Toast.LENGTH_SHORT).show()
            }
            nManagerCompact.notify(0,builder)
        }

    }
    //channel for notification
    fun createNotificationChannel(){
        if (Build.VERSION.SDK_INT >=Build.VERSION_CODES.O){
            val channel = NotificationChannel(CHANNEL_ID, CHANNEL_NAME,NotificationManager.IMPORTANCE_DEFAULT)

            val manager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            manager.createNotificationChannel(channel)
        }
    }
}