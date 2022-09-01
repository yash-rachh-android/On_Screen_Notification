package com.example.onscreennotification

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.RelativeLayout
import com.example.screen_notification.NotificationView

class MainActivity : AppCompatActivity() {

    var notificationView : NotificationView ?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        notificationView = findViewById(R.id.notificationView)
        notificationView!!.initComponents()
        notificationView!!.setNotificationView("Hello World!")
        notificationView!!.notifyView()
    }
}