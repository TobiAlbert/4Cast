package com.tobidaada.source.location

import android.content.Context
import android.content.SharedPreferences
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class SharedPreferenceManager @Inject constructor(@ApplicationContext private val context: Context) {

    companion object {
        private const val SHARED_PREF_NAME = "com.tobidaada_shared_pref"
        const val LATITUDE = "latitude"
        const val LONGITUDE = "longitude"
    }

    private fun getSharedPrefs(): SharedPreferences =
        context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)

    private fun getEditor(): SharedPreferences.Editor =
        getSharedPrefs().edit()

    fun saveDouble(key: String, value: Double) {
        getEditor().putFloat(key, value.toFloat())
    }

    fun getDouble(key: String, defaultValue: Double = 0.0) =
        getSharedPrefs().getFloat(key, defaultValue.toFloat()).toDouble()
}