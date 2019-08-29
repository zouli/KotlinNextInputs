package com.riverside.skeleton.kotlin.kotlinnextinputs.verifiers

import com.github.yoojia.inputs.Verifier
import java.util.regex.Pattern

/**
 * 正则表达式验证器 1.0
 * b_e  2019/08/14
 */
class RegexVerifier(private val regex: String) : Verifier {
    override fun perform(rawInput: String): Boolean = Pattern.matches(regex, rawInput)
}