package com.riverside.skeleton.kotlin.kotlinnextinputs

import android.app.Activity
import android.widget.TextView
import com.github.yoojia.inputs.Input
import com.github.yoojia.inputs.MessageDisplay
import com.github.yoojia.inputs.TextInput
import com.github.yoojia.inputs.ViewInput
import com.google.android.material.snackbar.Snackbar
import com.riverside.skeleton.kotlin.kotlinnextinputs.notice.*

/**
 * 错误提示Toast版    1.0
 * b_e  2019/07/25
 */
class AndroidToastMessageDisplay : MessageDisplay {
    override fun show(input: Input?, message: String?) {
        input?.let {
            when (it) {
                is TextInput<*> -> it.inputView
                is ViewInput<*> -> it.inputView
                else -> null
            }
        }?.run {
            if (TextView::class.java.isAssignableFrom(this::class.java)) {
                (this as TextView).requestFocus()
            }

            message?.toast(this.context.applicationContext)
        }
    }
}

/**
 * 错误提示Snackbar版    1.0
 * b_e  2019/07/25
 */
class AndroidSnackbarMessageDisplay : MessageDisplay {
    override fun show(input: Input?, message: String?) {
        input?.let {
            when (it) {
                is TextInput<*> -> it.inputView
                is ViewInput<*> -> it.inputView
                else -> null
            }
        }?.run {
            if (this.context !is Activity && TextView::class.java.isAssignableFrom(this::class.java)) {
                (this as TextView).requestFocus()
            }
            message?.snackbar(this.context)?.show()
        }
    }
}

/**
 * 不显示提示版    1.0
 * b_e  2019/07/31
 */
class BlankMessageDisplay : MessageDisplay {
    override fun show(input: Input?, message: String?) {
    }
}