package com.example.secondPackage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mainPackage.R

class MotionLayoutActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_motion_layout)
    }
}