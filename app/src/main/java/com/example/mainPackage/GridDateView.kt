package com.example.mainPackage

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import java.util.*

class GridDateView(private val context: Context, private val dateList:MutableList<String>) :
    BaseAdapter() {
    override fun getCount(): Int {
        return dateList.size
    }

    override fun getItem(position: Int): Any? {
        return null
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {
        val view = LayoutInflater.from(context).inflate(R.layout.date_item, null)
        val txvDay = view?.findViewById<TextView>(R.id.txv_pickerDay)
        txvDay?.text = dateList[position]
        return view
    }
}