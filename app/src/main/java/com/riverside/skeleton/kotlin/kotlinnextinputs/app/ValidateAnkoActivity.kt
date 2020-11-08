package com.riverside.skeleton.kotlin.kotlinnextinputs.app;

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity;
import com.github.yoojia.inputs.StaticScheme
import com.riverside.skeleton.kotlin.kotlinnextinputs.*
import org.jetbrains.anko.*
import org.jetbrains.anko.sdk27.coroutines.onClick

class ValidateAnkoActivity : AppCompatActivity() {
    lateinit var et_1: EditText
    lateinit var et_2: EditText
    lateinit var rb_show_snackbar: RadioButton
    lateinit var rg_1: RadioGroup
    lateinit var gl_1: GridLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        verticalLayout {
            lparams(matchParent, matchParent)

            rg_1 = radioGroup {
                orientation = RadioGroup.HORIZONTAL

                radioButton { text = "rb_1" }.lparams(wrapContent, wrapContent)
                radioButton { text = "rb_2" }.lparams(wrapContent, wrapContent)
                radioButton { text = "rb_3" }.lparams(wrapContent, wrapContent)
            }.lparams(matchParent, wrapContent)

            gl_1 = gridLayout { columnCount = 3 }.lparams(matchParent, wrapContent)

            linearLayout {
                orientation = LinearLayout.HORIZONTAL
                et_1 = editText {}.lparams(wrapContent, wrapContent, 1.0f)
                et_2 = editText {}.lparams(wrapContent, wrapContent, 1.0f)
            }.lparams(matchParent, wrapContent)

            button("do") {
                onClick {
                    doValidate()
                }
            }.lparams(matchParent, wrapContent)

            radioGroup {
                orientation = LinearLayout.HORIZONTAL

                val rb_show_toast = radioButton {
                    text = "Show Toast"
                }.lparams(wrapContent, wrapContent, 1.0f)

                rb_show_snackbar = radioButton {
                    text = "Show Snackbar"
                }.lparams(wrapContent, wrapContent, 1.0f)

                check(rb_show_toast.id)
            }.lparams(matchParent, wrapContent)
        }

        for (i in 0 until 6) {
            val cb = CheckBox(this)
            cb.id = R.id.cb_1
            cb.text = "cb_1"
            gl_1.addView(cb)
        }
    }

    fun doValidate(): Boolean = nextInput {
        messageDisplay =
            if (rb_show_snackbar.isChecked) AndroidSnackbarMessageDisplay() else AndroidToastMessageDisplay()

        //子项目至少选择一项
        add(rg_1.nextInput, StaticScheme.Required().msg("请在rb_1,rb_2,rb_3中选择一个"))

        //子项目至少选择一项
        add(
            gl_1.nextInput(checkableId = R.id.cb_1),
            StaticScheme.Required().msg("请至少选择一个cb_1")
        )

        //至少输入一项
        add(
            et_1.findOnceSatisfy(et_1.nextInput, et_2.nextInput),
            StaticScheme.Required().msg("文本框至少输入一项")
        )

        //当前有值时前项必须输入
        add(
            et_2.nextInput,
            SchemeExtend.RequiredAndPreviousRequired(et_1.lazyLoader)
        )

        //正则表达式检查
        add(
            et_1.nextInput,
            SchemeExtend.Regex(SchemeExtend.PASSWORD_8DL_REGEX).msg("请输入8位英文+数字")
        )
    }.test()
}
