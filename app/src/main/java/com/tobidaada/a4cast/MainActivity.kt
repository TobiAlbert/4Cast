package com.tobidaada.a4cast

import android.Manifest
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.tobidaada.a4cast.observers.LocationObserver
import com.tobidaada.a4cast.utils.requestUserPermission

class MainActivity : AppCompatActivity() {
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
            lifecycle.addObserver(LocationObserver(this))
        }
    }
}