package com.akshatsahijpal.socialgraph.ui.mainpage

import android.annotation.SuppressLint
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.akshatsahijpal.socialgraph.R
import com.akshatsahijpal.socialgraph.databinding.ActivityMainAppBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainPageActivity: AppCompatActivity() {
    private lateinit var _binding: ActivityMainAppBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainAppBinding.inflate(layoutInflater)
        setContentView(_binding.root)
        defaultSettings()
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView3)
                as NavHostFragment
        _binding.bottomNavigation.apply {
            background = null
            menu.getItem(2).isEnabled = false
            setupWithNavController(navHostFragment.findNavController())
            setOnNavigationItemReselectedListener { Unit }

            _binding.floatingActionButton.setOnClickListener {
                navHostFragment.findNavController().navigate(R.id.globalInto)
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_nav_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }
    @SuppressLint("SourceLockedOrientationActivity")
    private fun defaultSettings() {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        this.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
    }
}