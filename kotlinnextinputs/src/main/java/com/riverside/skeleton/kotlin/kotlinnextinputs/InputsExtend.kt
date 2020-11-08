package com.riverside.skeleton.kotlin.kotlinnextinputs

import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.github.yoojia.inputs.*

/**
 * Inputs基于Kotlin扩展    1.0
 * b_e  2019/08/28
 */

/**
 * WidgetProviders扩展
 */
val TextView.nextInput get() = WidgetProviders.textView(this)
val EditText.nextInput get() = WidgetProviders.editText(this)
val RadioButton.nextInput get() = WidgetProviders.radioButton(this)
val CheckBox.nextInput get() = WidgetProviders.checkBox(this)
val ToggleButton.nextInput get() = WidgetProviders.toggleButton(this)
val RatingBar.nextInput get() = WidgetProviders.ratingBar(this)
val CompoundButton.nextInput get() = WidgetProviders.checkable(this)
val RadioGroup.nextInput get() = WidgetProvidersExtend.radioGroup(this)
fun View.nextTagInput(id: Int) = WidgetProvidersExtend.tag(this, id)
fun ViewGroup.nextInput(childMinCount: Int = -1, checkableId: Int = -1) =
    when {
        childMinCount > -1 -> WidgetProvidersExtend.gridLayoutHasChild(this, childMinCount)
        checkableId > -1 -> WidgetProvidersExtend.gridLayoutSelectable(this, checkableId)
        else -> null
    }

/**
 * 至少输入一项
 */
fun View.findOnceSatisfy(vararg inputs: Input) =
    inputs.joinToString("") { it.value.trim() }
        .let { WidgetProvidersExtend.view(this, it) }

/**
 * LazyLoaders扩展
 */
val EditText.lazyLoader get() = LazyLoaders.fromEditText(this)
val TextView.lazyLoader get() = LazyLoaders.fromTextView(this)

/**
 * Inputs扩展
 */
fun nextInput(init: AndroidNextInputs.() -> Unit) = AndroidNextInputs().apply { init() }

var AndroidNextInputs.messageDisplay: MessageDisplay
    get() = AndroidToastMessageDisplay()
    set(value) {
        setMessageDisplay(value)
    }