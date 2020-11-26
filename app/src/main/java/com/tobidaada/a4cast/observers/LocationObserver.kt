package com.tobidaada.a4cast.observers

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.os.Looper
import androidx.core.content.ContextCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import com.google.android.gms.location.*
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class LocationObserver @Inject constructor(@ApplicationContext private val context: Context) : LifecycleObserver {

    companion object {
        private const val UPDATE_INTERVAL_IN_MILLISECONDS = 15_000L
        private const val FASTEST_UPDATE_INTERVAL_IN_MILLISECONDS = 15_000L
    }

    private lateinit var mFusedLocationClient: FusedLocationProviderClient
    private lateinit var mLocation: Location

    private val mLocationCallback: LocationCallback = object: LocationCallback() {
        override fun onLocationResult(result: LocationResult?) {
            super.onLocationResult(result)

            result ?: return

            mLocation = result.lastLocation
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreate() {
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(context)
        val hasPermission =
            ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED

        if (hasPermission) {
            mFusedLocationClient.lastLocation
                .addOnSuccessListener {
                    // update location only if response is not null
                    // and location has not already been updated.
                    if (it != null && !::mLocation.isInitialized) mLocation = it
            }
        }
        mFusedLocationClient.requestLocationUpdates(createLocationRequest(), mLocationCallback, Looper.getMainLooper())
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onDestroy() {
        mFusedLocationClient.removeLocationUpdates(mLocationCallback)
    }

    private fun createLocationRequest(): LocationRequest {
        return LocationRequest.create().apply {
            interval = UPDATE_INTERVAL_IN_MILLISECONDS
            fastestInterval = FASTEST_UPDATE_INTERVAL_IN_MILLISECONDS
            priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        }
    }
}