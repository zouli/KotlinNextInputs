package com.riverside.skeleton.kotlin.kotlinnextinputs.app

import android.os.Bundle
import android.widget.CheckBox
import androidx.appcompat.app.AppCompatActivity
import com.github.yoojia.inputs.StaticScheme
import com.riverside.skeleton.kotlin.kotlinnextinputs.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        for (i in 0 until 6) {
            val cb = CheckBox(this)
            cb.id = R.id.cb_1
            cb.text = "cb_1"
            gl_1.addView(cb)
        }

        btn_do.setOnClickListener {
            doValidate()
        }
    }

    fun doValidate(): Boolean = nextInput {
        messageDisplay =
            if (rb_show_snackbar.isChecked) AndroidSnackbarMessageDisplay() else AndroidToastMessageDisplay()

        add(rg_1.nextInput, StaticScheme.Required().msg("请在rb_1,rb_2,rb_3中选择一个"))
        add(
            gl_1.nextInput(checkableId = R.id.cb_1),
            StaticScheme.Required().msg("请至少选择一个cb_1")
        )
        add(
            et_1.findOnceSatisfy(et_1.nextInput, et_2.nextInput),
            StaticScheme.Required().msg("文本框至少输入一项")
        )
        add(
            et_2.nextInput,
            SchemeExtend.RequiredAndPreviousRequired(et_1.lazyLoader)
        )
        add(
            et_1.nextInput,
            SchemeExtend.Regex(SchemeExtend.PASSWORD_8DL_REGEX).msg("请输入8位英文+数字")
        )
    }.test()
}