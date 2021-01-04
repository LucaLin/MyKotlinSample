package com.example.mainPackage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.WindowManager
import android.widget.LinearLayout
import android.widget.PopupWindow
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_widget.*

class SeekBarTestActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_widget)

        seekBar?.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
            override fun onProgressChanged(sb: SeekBar?, progress: Int, fromUser: Boolean) {

//                txvResult?.text = progress.toString()
//
//                //計算文字顯示位置
//                var minusWidth = txvResult.width / 2//文字一半的寬度
//                var x = (sb?.thumb?.bounds?.left)
//                txvResult.x = x?.toFloat() ?: 0f

            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }

        })
    }
}