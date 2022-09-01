package com.example.screen_notification

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NotificationAdapter(var arrayList : MutableList<String>) : RecyclerView.Adapter<NotificationAdapter.ViewHolder>() {

    var mContext : Context ?= null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        mContext = parent.context
        val view = LayoutInflater.from(mContext).inflate(R.layout.item_notification,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvTitle.text = arrayList[position]
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    fun updateList(list : ArrayList<String>){
        arrayList.clear()
        arrayList.addAll(list)
        notifyDataSetChanged()
    }

    class ViewHolder(view : View) : RecyclerView.ViewHolder(view){
        val tvTitle : TextView = view.findViewById(R.id.tvNotifyText)
    }
}