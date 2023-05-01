package com.gun.android_marvel_example.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.gun.android_marvel_example.R
import com.gun.android_marvel_example.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding by lazy<ActivityMainBinding> { DataBindingUtil.setContentView(this, R.layout.activity_main) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.lifecycleOwner = this

        val navHostFragment = supportFragmentManager.findFragmentById(binding.navHostFragment.id) as NavHostFragment
        val navController = navHostFragment.findNavController()

        binding.bottomNavigation.setupWithNavController(navController)
    }
}