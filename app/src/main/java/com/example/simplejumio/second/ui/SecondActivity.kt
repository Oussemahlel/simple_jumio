package com.example.simplejumio.second.ui

import android.os.Bundle
import androidx.annotation.IdRes
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.example.simplejumio.BaseActivity
import com.example.simplejumio.BaseApplication
import com.example.simplejumio.R
import javax.inject.Inject

class SecondActivity : BaseActivity() {
    private lateinit var navController: NavController
    private lateinit var secondViewModel: SecondViewModel
    @Inject
    lateinit var providerFactory: ViewModelProvider.Factory
    override fun inject() {
        (application as BaseApplication).secondComponent().inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        inject()
        secondViewModel = ViewModelProvider(this, providerFactory)[SecondViewModel::class.java]
        val token = intent.extras?.getString("token")
        navController = findNavController(R.id.nav_jumio_fragment)
        val navGraph = navController.navInflater.inflate(R.navigation.second_nav_graph)

        @IdRes
        val navigation = R.id.nav_jumio_fragment
        navGraph.startDestination = navigation
        navController.setGraph(
            navGraph, bundleOf(
                "token" to token
            )
        )
    }
}