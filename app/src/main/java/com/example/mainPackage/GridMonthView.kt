package com.example.mainPackage

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import java.util.*

class GridMonthView(private val context: Context, private val monthList: Array<String>) :
    BaseAdapter() {
    override fun getCount(): Int {
        return monthList.size
    }

    override fun getItem(position: Int): Any? {
        return null
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = LayoutInflater.from(context).inflate(R.layout.month_picker_item, null)
        val month_txv = view.findViewById<TextView>(R.id.month_txv)
        month_txv.text = monthList[position]
        return view
    }
}