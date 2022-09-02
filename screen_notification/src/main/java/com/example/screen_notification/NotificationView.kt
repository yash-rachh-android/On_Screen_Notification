package com.example.screen_notification

import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import android.os.Handler
import android.util.AttributeSet
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView


open class NotificationView : RelativeLayout {

    var layout: LinearLayout? = null
    var notificationList = ArrayList<NotificationModel>()

    constructor(context: Context) : super(context) {}

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(
        context,
        attrs,
        defStyle
    ) {
    }

    constructor(context: Context, attrs: AttributeSet) : this(context, attrs, 0) {}

    open fun initComponents() {
        layout = View.inflate(context, R.layout.item_notification, null) as LinearLayout?
        val params = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        params.setMargins(20, 20, 20, 20)
        layout!!.layoutParams = params
        layout!!.visibility = View.GONE
        this.addView(layout)
    }

    open fun setNotificationText(title: String) {
        val tvTitle: TextView = layout!!.findViewById(R.id.tvNotifyText)
        tvTitle.text = title
    }

    open fun notifyView() {
        if(notificationList.isNotEmpty()){
            val tvTitle: TextView = layout!!.findViewById(R.id.tvNotifyText)
            tvTitle.text = notificationList[0].title
            if(notificationList[0].icon != null){
                val imgNotification: ImageView = layout!!.findViewById(R.id.imgNotify)
                imgNotification.visibility = View.VISIBLE
                imgNotification.setImageDrawable(notificationList[0].icon)
            }
            slideInAnimation(true)
        }else{
            slideInAnimation(false)
        }
    }

    private fun slideInAnimation(fromQue: Boolean) {
        val slideInAnimation: Animation =
            AnimationUtils.loadAnimation(context, R.anim.right_to_left)
        slideInAnimation.startOffset = 0
        layout!!.visibility = View.VISIBLE
        layout!!.startAnimation(slideInAnimation)
        slideInAnimation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(p0: Animation?) {

            }

            override fun onAnimationEnd(p0: Animation?) {
                Handler().postDelayed({
                    slideOutAnimation(fromQue)
                }, 1000)
            }

            override fun onAnimationRepeat(p0: Animation?) {

            }

        })
    }

    private fun slideOutAnimation(fromQue: Boolean) {
        val slideOutAnimation: Animation =
            AnimationUtils.loadAnimation(context, R.anim.slide_out_from_left)
        slideOutAnimation.startOffset = 0
        layout!!.startAnimation(slideOutAnimation)
        slideOutAnimation.setAnimationListener(object : Animation.AnimationListener{
            override fun onAnimationStart(p0: Animation?) {

            }

            override fun onAnimationEnd(p0: Animation?) {
                if(fromQue){
                    removeNotificationFromQue(0)
                    if(notificationList.isNotEmpty()){
                        val tvTitle: TextView = layout!!.findViewById(R.id.tvNotifyText)
                        tvTitle.text = notificationList[0].title
                        if(notificationList[0].icon != null){
                            val imgNotification: ImageView = layout!!.findViewById(R.id.imgNotify)
                            imgNotification.visibility = View.VISIBLE
                            imgNotification.setImageDrawable(notificationList[0].icon)
                        }
                        slideInAnimation(true)
                    }else{
                        layout!!.visibility = View.GONE
                    }
                }else {
                    layout!!.visibility = View.GONE
                }
            }

            override fun onAnimationRepeat(p0: Animation?) {

            }

        })
    }

    open fun setBackgroundColor(color : String){
        layout!!.setBackgroundColor(Color.parseColor(color))
    }

    open fun setBackgroundResource(drawable: Drawable?){
        layout!!.background = drawable
    }

    open fun setNotificationIcon(drawable: Drawable?){
        val imgNotification: ImageView = layout!!.findViewById(R.id.imgNotify)
        imgNotification.visibility = View.VISIBLE
        imgNotification.setImageDrawable(drawable)
    }

    open fun setTextColor(color: String){
        val tvTitle: TextView = layout!!.findViewById(R.id.tvNotifyText)
        tvTitle.setTextColor(Color.parseColor(color))
    }

    open fun setTextSize(size: Float){
        val tvTitle: TextView = layout!!.findViewById(R.id.tvNotifyText)
        tvTitle.textSize = size
    }

    open fun setTextBold(isBold: Boolean){
        val tvTitle: TextView = layout!!.findViewById(R.id.tvNotifyText)
        if(isBold){
            tvTitle.setTypeface(tvTitle.typeface, Typeface.BOLD)
        }
    }

    open fun setTextItalic(isBold: Boolean){
        val tvTitle: TextView = layout!!.findViewById(R.id.tvNotifyText)
        if(isBold){
            tvTitle.setTypeface(tvTitle.typeface, Typeface.ITALIC)
        }
    }

    open fun addNotificationToQue(notification: NotificationModel){
        notificationList.add(notification)
    }

    private fun removeNotificationFromQue(position: Int){
        notificationList.removeAt(position)
    }
}