package com.tobidaada.a4cast.presentation

import android.util.Log
import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tobidaada.domain.usecases.weather.GetCurrentWeather
import kotlinx.coroutines.launch

class MainViewModel @ViewModelInject constructor(
    private val getCurrentWeather: GetCurrentWeather,
    @Assisted private val savedStateHandle: SavedStateHandle
): ViewModel() {

    fun getWeather() {
        Log.i("MainViewModel", "Fetching Weather")
        viewModelScope.launch {
            val response = getCurrentWeather.invoke()
            Log.i("MainViewModel", response.toString())
        }
    }
}