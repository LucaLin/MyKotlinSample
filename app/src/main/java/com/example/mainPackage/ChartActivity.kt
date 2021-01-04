package com.example.mainPackage

import android.animation.ObjectAnimator
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.animation.AccelerateInterpolator
import android.view.animation.DecelerateInterpolator
import android.view.animation.TranslateAnimation
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.secondPackage.MotionLayoutActivity
import kotlinx.android.synthetic.main.activity_chart.*
import kotlinx.coroutines.*
import org.jetbrains.anko.textColor
import java.util.*

class ChartActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chart)

        //coorinatorlayout嵌RecyclerView的滾動處理https://ithelp.ithome.com.tw/articles/10206814
        changeTabPos(tab1)
        ryView.isNestedScrollingEnabled = false
        ryView.setNestedScrollingEnabled(false)
        ryView.setHasFixedSize(true)
        ryView.layoutManager = LinearLayoutManager(this)
        ryView.adapter = getAdapter()




        with(tab1) {
            this.setOnClickListener {

                ryView.layoutManager = LinearLayoutManager(context)
                ryView.adapter = getAdapter()

                changeTabPos(tab1)
//                line.x = tab1.x
            }
        }


        with(tab2) {
            this?.setOnClickListener {

                ryView.layoutManager = LinearLayoutManager(context)
                ryView.adapter = getAdapter()

//                var anim = TranslateAnimation(line.x,line.y,tab2.x,line.y)
//                anim.duration = 1000
//                anim.repeatCount = 0
//                line.startAnimation(anim)

                changeTabPos(tab2)

            }
        }


        with(tab3) {

            this?.setOnClickListener {


                ryView.layoutManager = LinearLayoutManager(context)
                ryView.adapter = getAdapter()

                changeTabPos(tab3)
//                line.x = tab3.x
            }
        }

        with(tab4) {
            this?.setOnClickListener { gotoMotionLayout() }

        }

        //滑動隱藏toolbar test
//        ryView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
//            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
//                super.onScrolled(recyclerView, dx, dy)
//                if(dy > 25){
//                    toolbarLayout.animate().translationY(-toolbarLayout.height.toFloat()).setInterpolator(AccelerateInterpolator(2f)).start()
//                    toolbarLayout.visibility = View.GONE
//                }
//                else if(dy < -25){
//                    toolbarLayout.visibility = View.VISIBLE
//                    toolbarLayout.animate().translationY(toolbarLayout.height.toFloat()).setInterpolator(AccelerateInterpolator(2f)).start()
//
//                }
//            }
//        })
//
    }

    fun getAdapter(): MyAdapter{
        var list: MutableList<Data> = mutableListOf()
        for (i in 1..5) {
            list.add(Data())
        }

        var adapter = MyAdapter(this, list)
        return adapter
    }

    fun changeTabPos(txvView: TextView){
        var anim = ObjectAnimator.ofFloat(line,"translationX",txvView.x)
        anim.duration = 250
        anim.start()
    }

    private fun gotoMotionLayout() {
        startActivity(Intent(this, MotionLayoutActivity::class.java))
    }

}
