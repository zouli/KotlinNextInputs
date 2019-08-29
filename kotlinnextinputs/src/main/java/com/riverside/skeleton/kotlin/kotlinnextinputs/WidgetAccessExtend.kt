package com.riverside.skeleton.kotlin.kotlinnextinputs

import com.github.yoojia.inputs.Input
import com.github.yoojia.inputs.WidgetAccess

/**
 * 控件访问扩展   1.1
 * b_e  2019/08/08
 */

/**
 * 取得RadioGroup是否选择
 */
fun WidgetAccess.findRadioGroup(viewId: Int): Input =
    WidgetProvidersExtend.radioGroup(findView(viewId))

/**
 * 取得View的tag的值
 */
fun WidgetAccess.findTag(viewId: Int, tagId: Int): Input =
    WidgetProvidersExtend.tag(findView(viewId), tagId)

/**
 * 取得GridLayout已选择Child
 */
fun WidgetAccess.findGridLayoutSelectable(gridLayoutId: Int, checkableId: Int): Input =
    WidgetProvidersExtend.gridLayoutSelectable(findView(gridLayoutId), checkableId)

/**
 * 取得GridLayout是否包含Child
 */
fun WidgetAccess.findGridLayoutHasChild(gridLayoutId: Int, childMinCount: Int): Input =
    WidgetProvidersExtend.gridLayoutHasChild(findView(gridLayoutId), childMinCount)

/**
 * 取得最少一项为true
 */
fun WidgetAccess.findOnceSatisfy(viewId: Int, vararg inputs: Input): Input =
    inputs.map { it.value.trim() }.joinToString { "" }
        .let { WidgetProvidersExtend.view(findView(viewId), it) }