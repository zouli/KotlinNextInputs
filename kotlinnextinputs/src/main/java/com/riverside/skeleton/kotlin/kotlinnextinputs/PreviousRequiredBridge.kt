package com.riverside.skeleton.kotlin.kotlinnextinputs

import com.github.yoojia.inputs.PairedVerifier
import com.github.yoojia.inputs.TextLazyLoader

/**
 * 验证扩展 1.0
 * 本项不为空时前一项不能为空
 * <p>
 * b_e  2019/08/14
 */
class PreviousRequiredBridge(lazyLoader: TextLazyLoader) :
    PairedVerifier<String>(lazyLoader.value, lazyLoader.value) {
    private val mStringLazyLoader = lazyLoader

    override fun benchmark1stValueForMessage(): String = mStringLazyLoader.value

    override fun benchmark2ndValueForMessage(): String = mStringLazyLoader.value

    override fun performTyped(typedInput: String): Boolean =
        typedInput.isNotEmpty() && mStringLazyLoader.value.isNotEmpty()

    override fun typedCast(input: String): String = input
}