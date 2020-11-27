package com.tobidaada.a4cast.presentation

import android.Manifest
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.tobidaada.a4cast.R
import com.tobidaada.a4cast.observers.LocationObserver
import com.tobidaada.a4cast.utils.requestUserPermission
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    @Inject lateinit var locationObserver: LocationObserver

    private lateinit var mNavController: NavController
    private val mBottomNavListener = BottomNavigationView.OnNavigationItemSelectedListener {
        return@OnNavigationItemSelectedListener when(it.itemId) {
            R.id.action_today -> {
                this.mNavController.navigate(R.id.todayWeatherFragment)
                true
            }
            R.id.action_weekly -> {
                this.mNavController.navigate(R.id.weeklyWeatherFragment)
                true
            }
            R.id.action_share -> true
            else -> false
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        requestLocationPermission()
    }

    private fun requestLocationPermission() {
        val permissions = listOf(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
        )

        requestUserPermission(permissions) {
            // start listening for location updates
            lifecycle.addObserver(locationObserver)
            setupUi()
        }
    }

    private fun setupUi() {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment
        mNavController = navHostFragment.navController

        val bottomNav = findViewById<BottomNavigationView>(R.id.bottomNav)
        bottomNav.setOnNavigationItemSelectedListener(mBottomNavListener)
    }
}