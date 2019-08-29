package com.riverside.skeleton.kotlin.kotlinnextinputs

import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import android.widget.RadioGroup
import com.github.yoojia.inputs.ViewInput
import com.github.yoojia.inputs.WidgetProviders

/**
 * 选择器扩展    1.2
 * b_e  2019/08/08
 */
class WidgetProvidersExtend : WidgetProviders() {
    companion object {

        /**
         * 取得RadioGroup是否选择
         */
        fun radioGroup(radioGroup: RadioGroup): ViewInput<RadioGroup> =
            object : ViewInput<RadioGroup>(radioGroup) {
                override fun getValue(): String = if (radioGroup.checkedRadioButtonId == -1) "" else "true"
            }

        /**
         * 取得View的值
         */
        fun view(view: View, value: String) =
            object : ViewInput<View>(view) {
                override fun getValue(): String = value
            }

        /**
         * 取得View的tag值
         */
        fun tag(view: View, id: Int) =
            object : ViewInput<View>(view) {
                override fun getValue(): String =
                    if (when (id) {
                            0 -> view.tag?.toString() ?: ""
                            else -> view.getTag(id)?.toString() ?: ""
                        }.isEmpty()
                    ) "" else "true"
            }

        /**
         * 取得GirdLayout是否包含Child
         */
        fun gridLayoutHasChild(glView: ViewGroup, childMinCount: Int) =
            object : ViewInput<ViewGroup>(glView) {
                override fun getValue(): String = if (glView.childCount > childMinCount) "true" else ""
            }

        /**
         * 取得GirdLayout是否包含已选择的Child
         */
        fun gridLayoutSelectable(glView: ViewGroup, checkableId: Int) =
            object : ViewInput<ViewGroup>(glView) {
                override fun getValue(): String =
                    if (gridLayoutHasChild(glView, 0).value.isEmpty()) "" else {
                        if ((0 until glView.childCount).asSequence()
                                .map { glView.getChildAt(it) }
                                .map {
                                    when (it) {
                                        is ViewGroup -> it.findViewById(checkableId)
                                        else -> it
                                    }
                                }
                                .filter { it.id == checkableId }
                                .map { it as CompoundButton }
                                .map { it.isChecked }
                                .reduce { acc, b -> acc or b }
                        ) "true" else ""
                    }
            }
    }
}