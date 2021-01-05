package com.example.mainPackage

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_coordinator_test.*
import org.jetbrains.anko.backgroundResource


class CoordinatorTestActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coordinator_test)

        setSupportActionBar(toolbar)

        //左上角icon動作
        toolbar.setNavigationOnClickListener{
            Toast.makeText(this,"test",Toast.LENGTH_SHORT).show()
        }
//        supportActionBar?.setDisplayHomeAsUpEnabled(true)
//        supportActionBar?.setHomeAsUpIndicator(R.mipmap.icon_close)

        ryTest.isNestedScrollingEnabled = false
        ryTest.setNestedScrollingEnabled(false)
        ryTest.setHasFixedSize(true)
        ryTest.layoutManager = LinearLayoutManager(this)
        ryTest.adapter = getAdapter()
    }

    fun btnIncome(view: View){
        btnIncome.backgroundResource = R.drawable.btn_cancel_bg
        btnNow.backgroundResource = R.drawable.btn_toolbar_confirm_bg

    }
    fun btnNow(view: View){
        btnIncome.backgroundResource = R.drawable.btn_toolbar_confirm_bg
        btnNow.backgroundResource = R.drawable.btn_cancel_bg
    }



    fun getAdapter():MyAdapter{
        var list: MutableList<Data> = mutableListOf()
        for (i in 1..5) {
            list.add(Data())
        }

        var adapter = MyAdapter(this, list)
        return adapter
    }
}