package com.example.myapplication

import android.app.*
import android.content.Context
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import android.util.Log

class ForegroundService: Service() {
    override fun onBind(intent: Intent) = MyBinder()

    override fun onCreate() {
        super.onCreate()
        setForeground()

        Log.d(javaClass.simpleName, "onCreate......")
    }


    private fun setForeground() {
        val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val builder = if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            var channel = manager.getNotificationChannel("MyChannel")
            if (channel == null) {
                val importance = NotificationManager.IMPORTANCE_DEFAULT
                channel = NotificationChannel("MyChannel", "MyChannel", importance)
                manager.createNotificationChannel(channel)
            }
            Notification.Builder(this, channel.id)
        } else {
            Notification.Builder(this)
        }

        val intent = Intent(this, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT or Intent.FLAG_ACTIVITY_SINGLE_TOP
        val pendingIntent = PendingIntent.getActivity(this, 0, intent, 0)

        builder.setSmallIcon(R.mipmap.ic_launcher)
            .setContentTitle("这是标题")
            .setContentText("这是内容文字")
            .setContentIntent(pendingIntent)

        val notification: Notification = builder.build()
        manager.notify(0, notification)
        startForeground(0, notification)
    }

    inner class MyBinder : Binder() {
        fun getService(): ForegroundService = this@ForegroundService
    }

}