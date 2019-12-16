package com.riverside.skeleton.kotlin.kotlinnextinputs.app;

import android.os.Bundle
import android.widget.CheckBox
import androidx.appcompat.app.AppCompatActivity
import com.github.yoojia.inputs.AndroidNextInputs
import com.github.yoojia.inputs.LazyLoaders
import com.github.yoojia.inputs.StaticScheme
import com.github.yoojia.inputs.WidgetAccess
import com.riverside.skeleton.kotlin.kotlinnextinputs.*
import kotlinx.android.synthetic.main.activity_validate_xml.*

class ValidateXmlActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_validate_xml)

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

    fun doValidate(): Boolean {
        val snackbarMessageDisplay = AndroidSnackbarMessageDisplay()
        val toastMessageDisplay = AndroidToastMessageDisplay()
        val blankMessageDisplay = BlankMessageDisplay()
        val access = WidgetAccess(this)

        val loader = LazyLoaders(this)
        val inputs = AndroidNextInputs()

        inputs.setMessageDisplay(
            if (rb_show_snackbar.isChecked) snackbarMessageDisplay else toastMessageDisplay
        )

        inputs.add(
            access.findRadioGroup(R.id.rg_1),
            StaticScheme.Required().msg("请在rb_1,rb_2,rb_3中选择一个")
        )
        inputs.add(
            access.findGridLayoutSelectable(R.id.gl_1, R.id.cb_1),
            StaticScheme.Required().msg("请至少选择一个cb_1")
        )
        inputs.add(
            access.findOnceSatisfy(
                R.id.et_1,
                access.findEditText(R.id.et_1),
                access.findEditText(R.id.et_2)
            ),
            StaticScheme.Required().msg("文本框至少输入一项")
        )
        inputs.add(
            access.findEditText(R.id.et_2),
            SchemeExtend.RequiredAndPreviousRequired(loader.fromEditText(R.id.et_1))
        )

        return inputs.test()
    }
}
