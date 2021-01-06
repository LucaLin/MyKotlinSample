package com.example.mainPackage

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import org.jetbrains.anko.backgroundResource
import java.util.*

class GridDateView(private val context: Context, private val dateList: MutableList<DateItem>) :
    BaseAdapter() {

    var selectedList = mutableListOf<Int>()

    override fun getCount(): Int {
        return dateList.size
    }

    override fun getItem(position: Int): Any? {
        return null
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    //取得點選過的日期清單
    fun getSelectedDayList(): MutableList<Int> {
        return selectedList
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {
        val view = LayoutInflater.from(context).inflate(R.layout.date_picker_item, null)
        val txvDay = view?.findViewById<TextView>(R.id.txv_pickerDay)

        var item = dateList[position]

        txvDay?.text = item.day

        //todo: 多個日期有上限嗎？
        txvDay?.setOnClickListener {

            if (!item.isSelected) {
                txvDay.backgroundResource = R.drawable.date_picker_selected_bg
                item.isSelected = true

                selectedList.add(position+1)
            }else{
                txvDay.backgroundResource = R.color.whiteColor
                item.isSelected = false

                selectedList.remove(position+1)
            }

        }

        return view
    }
}