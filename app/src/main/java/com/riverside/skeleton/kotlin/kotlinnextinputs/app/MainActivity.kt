package com.riverside.skeleton.kotlin.kotlinnextinputs.app

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.jetbrains.anko.*
import org.jetbrains.anko.sdk27.coroutines.onClick

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        verticalLayout {
            button("validate anko") {
                onClick {
                    startActivity<ValidateAnkoActivity>()
                }
            }.lparams(matchParent, wrapContent)

            button("validate xml") {
                onClick {
                    startActivity<ValidateXmlActivity>()
                }
            }.lparams(matchParent, wrapContent)
        }
    }
}