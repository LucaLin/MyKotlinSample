package com.example.mainPackage

import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import java.text.SimpleDateFormat
import java.util.*


class ChartAxisFormatter(private val dateList: MutableList<String>) :
    IndexAxisValueFormatter() {
    // https://github.com/PhilJay/MPAndroidChart/issues/2283
    // If you are using version 3.1.0, the setValueFormatter method no longer supports IAxisValueFormatter
    private val TAG = ChartAxisFormatter::class.java.simpleName
    private val ymdSdf = SimpleDateFormat("MM-dd", Locale.ENGLISH)
    private val mdSdf = SimpleDateFormat("MM月dd日", Locale.ENGLISH)
    override fun getFormattedValue(value: Float): String {
        //LogTool.getInstance().printLog(TAG, "value = " + value);
        var date: Date? = null
        try {
            val index = value.toInt()
            if (index >= 0 && index < dateList.size) {
                date = ymdSdf.parse(dateList[index])
                return mdSdf.format(date)
            }
        } catch (e: Exception) {
            e.message
        }
        return ""
    }
}
