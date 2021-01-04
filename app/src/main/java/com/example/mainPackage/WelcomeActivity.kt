package com.example.mainPackage

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.view.*
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_welcome.*
import kotlinx.android.synthetic.main.num_pad_layout.*

class WelcomeActivity : AppCompatActivity() {

    lateinit var numClickListener: NumClickListener

    interface NumClickListener {
        open fun onNumClick(num: String)
    }

    fun setOnNumClickListener(numClickListener: NumClickListener) {
        this.numClickListener = numClickListener
    }

    var numPadDialog: AlertDialog? = null
    var monthPicker: AlertDialog? = null

    fun btnToChart(view:View){
        startActivity(Intent(this, ChartActivity::class.java))
    }

    fun btnToSeekBar(view:View){
        startActivity(Intent(this, SeekBarTestActivity::class.java))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)


    }

    //numPadLayout
    fun btnToNumKb(view: View){

        numPadDialog = createDialog(numPadDialog);
        var numPadDialogView = LayoutInflater.from(this).inflate(R.layout.num_pad_layout, null)
        var txvNumResult: TextView = numPadDialogView.findViewById(R.id.txvNumResult)
        var num_1: TextView = numPadDialogView.findViewById(R.id.num_1)
        var num_2: TextView = numPadDialogView.findViewById(R.id.num_2)
        var num_3: TextView = numPadDialogView.findViewById(R.id.num_3)
        var num_4: TextView = numPadDialogView.findViewById(R.id.num_4)
        var num_5: TextView = numPadDialogView.findViewById(R.id.num_5)
        var num_6: TextView = numPadDialogView.findViewById(R.id.num_6)
        var num_7: TextView = numPadDialogView.findViewById(R.id.num_7)
        var num_8: TextView = numPadDialogView.findViewById(R.id.num_8)
        var num_9: TextView = numPadDialogView.findViewById(R.id.num_9)
        var num_0: TextView = numPadDialogView.findViewById(R.id.num_0)
        var num_dot: TextView = numPadDialogView.findViewById(R.id.num_dot)
        var num_back: TextView = numPadDialogView.findViewById(R.id.num_back)
        var btn_cancel: Button = numPadDialogView.findViewById(R.id.btn_cancel)
        var btnConfirm: Button = numPadDialogView.findViewById(R.id.btn_confirm)

        var result: String = ""

        initNumList(
            mutableListOf(
                num_1,
                num_2,
                num_3,
                num_4,
                num_5,
                num_6,
                num_7,
                num_8,
                num_9
            )
        )

//            //點點鍵logic??
        num_dot.setOnClickListener {
            if (txvNumResult?.text.toString().equals("0.00")) {//第一次按"."
                result = "0."
                txvNumResult?.text = result
                num_dot.isEnabled = false
            } else {//其它情況
                result += "."
                txvNumResult?.text = result
                num_dot.isEnabled = false
            }

        }

        num_0.setOnClickListener {
            if (txvNumResult.text.toString().first() != '0' || txvNumResult.text.toString()
                    .matches(Regex("^[0]{1}."))
            ) {
                setNumPadClickEvent(num_0)
            }
        }

        //back鍵
        num_back.setOnClickListener {

            if (txvNumResult.text.toString().equals("0.00")) {//初始不啟動back鍵
                return@setOnClickListener
            }

            if (!TextUtils.isEmpty(txvNumResult?.text.toString()) && !txvNumResult.text.toString()
                    .equals("0.00")//有值而且不是初始值時
            ) {
                //刪除最後一碼
                txvNumResult?.text =
                    txvNumResult?.text.toString().substring(0, txvNumResult?.text.length - 1)

                if (txvNumResult?.text.toString().length == 0) {//刪去最後一碼時，資訊歸零
                    txvNumResult?.text = "0.00"
                    result = ""
                    num_dot.isEnabled = true
                }
            }


        }

        btn_cancel.setOnClickListener { numPadDialog?.dismiss() }


        numPadDialog?.setView(numPadDialogView)
        numPadDialog?.show()


        txvNumResult.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(
                s: CharSequence?,
                start: Int,
                count: Int,
                after: Int
            ) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                var str = s ?: null
                if (!TextUtils.isEmpty(str)) {
                    if (str?.toString().equals("0.00") || str?.toString()
                            ?.first() != '0'
                    )//首字不為0才啟動0鍵
                        num_0.isEnabled = true
                } else {//未鍵入任何字時，0鍵不啟動
                    num_0.isEnabled = false
                }
            }

            override fun afterTextChanged(s: Editable?) {
                var str = s ?: null
                if (str?.toString().equals("0") || str.toString()
                        .matches(Regex("[0]{1}.")) && !num_dot.isEnabled
                ) {
                    result = ""
                    txvNumResult.text = "0.00"
                    num_dot.isEnabled = true
                }

                if (str.toString().matches(Regex("[0-9]+"))) {
                    result = txvNumResult.text.toString()
                    num_dot.isEnabled = true
                }

                if (str.toString().equals("0.00"))
                    result = ""

            }
        })


        setOnNumClickListener(object : NumClickListener {
            override fun onNumClick(num: String) {
                if (!txvNumResult?.text.toString().equals("0.00"))
                    result = txvNumResult?.text.toString()//更新數字
                result += num
                txvNumResult?.text = result
            }
        })
    }

    fun btnToMonthPicker(view: View){

        monthPicker = createDialog(monthPicker)
        monthPicker?.window?.setGravity(Gravity.CENTER)
        var monthDatePickView = LayoutInflater.from(this).inflate(R.layout.month_picker_layout,null)

        //置中
        var display = windowManager.defaultDisplay
        var params = monthPicker?.window?.attributes
        params?.height = (display.height *0.5).toInt()
        monthPicker?.window?.attributes = params

        monthPicker?.setView(monthDatePickView)
        monthPicker?.show()

    }

    // ----------for numPadLayout

    fun initNumList(numList: MutableList<TextView>) {
        for (i in 0 until numList.size) {
            setNumPadClickEvent(numList.get(i))
        }
    }

    fun setNumPadClickEvent(num: TextView) {
        num?.setOnClickListener { numClickListener?.onNumClick(num?.text.toString()) }
    }
    // ----------for numPadLayout


    fun createDialog(dialog: AlertDialog?): AlertDialog? {
        if (dialog != null && dialog.isShowing) {
            dialog.dismiss()
        }
        val builder = AlertDialog.Builder(this, R.style.CustomDialog)
        return builder.create()
    }


}