package com.example.simplejumio

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity


abstract class BaseActivity : AppCompatActivity() {

    abstract fun inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as? BaseApplication)
            ?.appComponent?.inject(this)
        super.onCreate(savedInstanceState)
    }

}