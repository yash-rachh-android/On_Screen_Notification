package com.example.screen_notification

import android.content.Context
import android.os.Handler
import android.util.AttributeSet
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView


open class NotificationView : RelativeLayout {

    /*var notificationRecycler: RecyclerView? = null*/
    var notificationQueList = ArrayList<String>()

    /*var notificationAdapter = NotificationAdapter(mutableListOf())*/
    var layout: LinearLayout? = null

    constructor(context: Context) : super(context) {}

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(
        context,
        attrs,
        defStyle
    ) {
    }

    constructor(context: Context, attrs: AttributeSet) : this(context, attrs, 0) {}

    open fun initComponents() {
        /* notificationRecycler = RecyclerView(context)
         val params = RecyclerView.LayoutParams(
             RecyclerView.LayoutParams.MATCH_PARENT,
             RecyclerView.LayoutParams.WRAP_CONTENT
         )
         notificationRecycler?.layoutParams = params
         val layoutManager = LinearLayoutManager(context)
         layoutManager.orientation = LinearLayoutManager.HORIZONTAL
         notificationRecycler?.layoutManager = layoutManager
         notificationRecycler?.adapter = notificationAdapter
         this.addView(notificationRecycler)*/
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

    fun loadComponents(userList: ArrayList<String>) {
        notificationQueList.addAll(userList)
        /*notificationAdapter.updateList(notificationQueList)*/
    }

    fun clearQueList() {
        notificationQueList.clear()
        /* notificationAdapter.updateList(notificationQueList)*/
    }

    open fun setNotificationView(title: String) {
        val tvTitle: TextView = layout!!.findViewById(R.id.tvNotifyText)
        tvTitle.text = title
    }

    open fun notifyView() {
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
                    slideOutAnimation()
                }, 1000)
            }

            override fun onAnimationRepeat(p0: Animation?) {

            }

        })
    }

    private fun slideOutAnimation() {
        val slideOutAnimation: Animation =
            AnimationUtils.loadAnimation(context, R.anim.slide_out_from_left)
        slideOutAnimation.startOffset = 0
        layout!!.startAnimation(slideOutAnimation)
        slideOutAnimation.setAnimationListener(object : Animation.AnimationListener{
            override fun onAnimationStart(p0: Animation?) {

            }

            override fun onAnimationEnd(p0: Animation?) {
                layout!!.visibility = View.GONE
            }

            override fun onAnimationRepeat(p0: Animation?) {

            }

        })
    }
}