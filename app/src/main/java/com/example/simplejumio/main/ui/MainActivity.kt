package com.example.simplejumio.main.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.example.simplejumio.BaseActivity
import com.example.simplejumio.BaseApplication
import com.example.simplejumio.R
import com.example.simplejumio.second.ui.SecondActivity
import javax.inject.Inject

class MainActivity : BaseActivity() {
    private lateinit var startJumioBtn: Button
    private lateinit var mainViewModel: MainViewModel

    @Inject
    lateinit var providerFactory: ViewModelProvider.Factory
    override fun inject() {
        (application as BaseApplication).mainComponent()
            .inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        inject()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mainViewModel = ViewModelProvider(this, providerFactory)[MainViewModel::class.java]
        startJumioBtn = findViewById(R.id.setup_sdk_btn)

        startJumioBtn.setOnClickListener {
            val intent = Intent(this, SecondActivity::class.java)
            startActivity(intent)
        }
    }
}