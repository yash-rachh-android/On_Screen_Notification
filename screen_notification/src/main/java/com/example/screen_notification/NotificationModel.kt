package com.example.screen_notification

import android.graphics.drawable.Drawable

data class NotificationModel(
    var icon : Drawable?,
    var title : String = ""
) {
}