package com.riverside.skeleton.kotlin.kotlinnextinputs

import com.github.yoojia.inputs.Scheme
import com.github.yoojia.inputs.TextLazyLoader
import com.riverside.skeleton.kotlin.kotlinnextinputs.verifiers.RegexVerifier

/**
 * Scheme扩展 1.0
 * b_e  2019/08/14
 */
class SchemeExtend {
    companion object {
        // 8位，英文+数字
        val PASSWORD_8DL_REGEX = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{8,}$"

        /**
         * 本项不为空时前一项不能为空
         */
        fun RequiredAndPreviousRequired(lazyLoader: TextLazyLoader): Scheme =
            Scheme(PreviousRequiredBridge(lazyLoader)).msg("前一项不能为空")

        /**
         * 验证正则表达式
         */
        fun Regex(regex: String): Scheme = Scheme(
            RegexVerifier(
                regex
            )
        ).msg("此为非法字符串")
    }
}