package com.example.mainPackage

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.*
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import org.jetbrains.anko.backgroundColor
import org.jetbrains.anko.backgroundResource
import java.text.SimpleDateFormat
import java.util.*

class MyAdapter(val context: Context, var list: List<Data>) :
    RecyclerView.Adapter<MyAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
//        val view = LayoutInflater.from(context).inflate(R.layout.chartdata_item, null)//含其它資訊的
        val view = LayoutInflater.from(context).inflate(R.layout.chartdata_item_full, null)//全景

        return ViewHolder(view)
    }

    override fun onBindViewHolder(vh: ViewHolder, position: Int) {

        val buyEntrylist: MutableList<Entry> = mutableListOf()
        val dateList : MutableList<String> = mutableListOf()
        for(i in 1..30){
            var r = ((Math.random()+1)*100)
            var e = Entry((i + 1).toFloat(), r.toFloat())
            buyEntrylist.add(e)
        }

        //日期x軸test
        var c = Calendar.getInstance()
        for(i in 1..30){
            c.add(Calendar.DAY_OF_YEAR,1)
            var date = SimpleDateFormat("MM-dd").format(c.time)
            dateList.add(date)
        }

        //x軸 日期區間  y軸 金額、百分比
        val sellEntrylist: MutableList<Entry> = mutableListOf()

            sellEntrylist.add(Entry(20f,20f ))
            sellEntrylist.add(Entry(156f,156f ))

        var buyDataSet = LineDataSet(buyEntrylist, "")
        buyDataSet.setColor(context.getColor(R.color.chartLineColor))
        buyDataSet.lineWidth = 1.2f
        buyDataSet.setDrawCircles(false)
        buyDataSet.setDrawValues(false)

        var sellDataSet = LineDataSet(sellEntrylist, "")
        sellDataSet.setColor(context.getColor(R.color.chartLineColor))
        sellDataSet.lineWidth = 1.2f
        sellDataSet.setDrawCircles(false)
        sellDataSet.setDrawValues(false)
//        sellDataSet.enableDashedLine(10f,10f,0f)//虛線樣式

        var lineData = LineData(buyDataSet)

        vh.chart.setTouchEnabled(false)
        vh.chart.setScaleEnabled(false)
        vh.chart.backgroundResource = R.drawable.card_shape

        // description label 圖表資訊
        vh.chart.description.isEnabled = false
        // Legend 圖例數據
        vh.chart.legend.isEnabled = false
        vh.chart.data = lineData
        vh.chart.setDrawBorders(false)//

        var limitLine = LimitLine(50f, "")
        limitLine.lineWidth =1f
        limitLine.lineColor = Color.BLUE

        val rightAxis: YAxis = vh.chart.getAxisRight()//右邊y軸
        rightAxis.setDrawLabels(true)//y軸數據
        rightAxis.setDrawGridLines(false)
        rightAxis.setDrawAxisLine(false)//不要右邊的邊線
        rightAxis.textSize = 10f
        rightAxis.textColor = context.getColor(R.color.chartTxvColor)

        val leftAxis: YAxis = vh.chart.getAxisLeft()//左邊y軸

        leftAxis.setDrawLabels(false)//y軸數據
        leftAxis.setDrawZeroLine(false)
        leftAxis.setDrawGridLines(false)//去除y軸的格線
        leftAxis.setDrawAxisLine(false)//不要左邊的邊線
//        leftAxis.axisMinimum = 20f
//        leftAxis.setLabelCount(5,false)
//        leftAxis.addLimitLine(limitLine)

        val xAxis: XAxis = vh.chart.getXAxis()//x軸
        xAxis.setDrawLabels(true)//標籤
        xAxis.setDrawAxisLine(true)
        xAxis.setDrawGridLines(true)
        xAxis.textColor = context.getColor(R.color.chartTxvColor)
        xAxis.textSize = 10f
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM)
        xAxis.setAxisMaximum (dateList.size.toFloat())
        xAxis.valueFormatter = ChartAxisFormatter(dateList = dateList)//x軸數據樣式

    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        var chart = itemView.findViewById<LineChart>(R.id.lineChart)
    }

}
