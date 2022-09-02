package com.example.onscreennotification

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.RelativeLayout
import androidx.core.content.ContextCompat
import com.example.screen_notification.NotificationModel
import com.example.screen_notification.NotificationView

class MainActivity : AppCompatActivity() {

    var notificationView: NotificationView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        notificationView = findViewById(R.id.notificationView)

        //Initialise the View
        notificationView!!.initComponents()

        //Set Custom Background
        notificationView!!.setBackgroundResource(
            ContextCompat.getDrawable(
                this,
                R.drawable.bg_notification
            )
        )

        //Set Custom Text Size
        notificationView!!.setTextSize(17f)

        //Set Text to bold
        notificationView!!.setTextBold(true)

        //Set Text to italic
        notificationView!!.setTextItalic(true)

        //add notification objects to que
        for (i in 0..2) {
            notificationView!!.addNotificationToQue(
                NotificationModel(
                    ContextCompat.getDrawable(
                        this,
                        R.drawable.ic_launcher_background
                    ), "This is a test notification.==>$i"
                )
            )
        }

        //Or directly set the text and icon for single notification
        notificationView!!.setNotificationText("Single Notification!")
        notificationView!!.setNotificationIcon(ContextCompat.getDrawable(this,R.drawable.ic_launcher_background))

        notificationView!!.notifyView()
    }
}