package com.riverside.skeleton.kotlin.kotlinnextinputs.notice

import android.app.Activity
import android.content.Context
import android.view.View
import android.widget.TextView
import com.google.android.material.snackbar.Snackbar
import com.riverside.skeleton.kotlin.kotlinnextinputs.R

/**
 * Snackbar帮助  1.0
 * b_e                            2019/05/21
 */
fun String.snackbar(
    context: Context,
    duration: Int = SnackbarHelper.LENGTH_LONG,
    snackbarCallback: Snackbar.Callback? = null,
    messageTextColor: Int? = null,
    actionTitle: String? = null,
    actionListener: View.OnClickListener? = null,
    actionTitleColor: Int? = null
) = with(SnackbarHelper(context)) {
    this.snackbarCallback = snackbarCallback
    this.messageTextColor = messageTextColor
    this.actionTitle = actionTitle
    this.actionListener = actionListener
    this.actionTitleColor = actionTitleColor
    this.showSnackbar(this@snackbar, duration)
}

class SnackbarHelper constructor(context: Context) {
    private val mActivity = context
    //当前Activity的根View
    private var rootView: View
    //Snackbar的显示回调
    var snackbarCallback: Snackbar.Callback? = null
    //Action的文字
    var actionTitle: String? = null
    //Action文字的颜色
    var actionTitleColor: Int? = null
    //Action的点击事件
    var actionListener: View.OnClickListener? = null
    //文字的颜色
    var messageTextColor: Int? = null

    init {
        rootView = (mActivity as Activity).window.decorView.findViewById<View>(android.R.id.content)
    }

    /**
     * 显示Snackbar
     *
     * @param message
     * @param duration
     */
    fun showSnackbar(message: String, @Snackbar.Duration duration: Int) {
        val snackbar = Snackbar.make(rootView, message, duration)
        actionTitleColor?.let { snackbar.setActionTextColor(it) }
        messageTextColor?.let { snackbar.view.findViewById<TextView>(R.id.snackbar_text).setTextColor(it) }

        actionTitle?.let { snackbar.setAction(it, actionListener) }

        snackbarCallback?.let {
            snackbar.removeCallback(it)
            snackbar.addCallback(it)
        }

        snackbar.show()
    }

    companion object {
        var LENGTH_SHORT = 1777
        var LENGTH_LONG = 3034
    }
}


