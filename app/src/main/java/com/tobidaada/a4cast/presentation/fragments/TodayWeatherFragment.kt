package com.tobidaada.a4cast.presentation.fragments

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.squareup.picasso.Picasso
import com.tobidaada.a4cast.R
import com.tobidaada.a4cast.presentation.MainViewModel
import com.tobidaada.a4cast.presentation.models.CurrentWeather
import com.tobidaada.a4cast.presentation.models.Status
import com.tobidaada.a4cast.utils.formatDateFromUnixTime
import com.tobidaada.a4cast.utils.getCityFromTimezone
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TodayWeatherFragment : Fragment(R.layout.fragment_today_weather) {

    private val mainViewModel: MainViewModel by viewModels()

    private lateinit var mCityTv: TextView
    private lateinit var mDateTv: TextView
    private lateinit var mDescriptionTv: TextView
    private lateinit var mCurrentTemperatureTv: TextView
    private lateinit var mFeelsLikeTempTv: TextView
    private lateinit var mWindSpeedTv: TextView
    private lateinit var mUvIndexTv: TextView
    private lateinit var mHumidityTv: TextView
    private lateinit var mIcon: ImageView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupUi(view)
        observeViewModel()
    }

    private fun setupUi(view: View) {
        mCityTv = view.findViewById(R.id.cityName)
        mDateTv = view.findViewById(R.id.date)
        mDescriptionTv = view.findViewById(R.id.description)
        mCurrentTemperatureTv = view.findViewById(R.id.currentTemperature)
        mFeelsLikeTempTv = view.findViewById(R.id.feelsLikeTemp)
        mWindSpeedTv = view.findViewById(R.id.windSpeed)
        mUvIndexTv = view.findViewById(R.id.uvIndex)
        mHumidityTv = view.findViewById(R.id.humidity)
        mIcon = view.findViewById(R.id.icon)
    }

    private fun observeViewModel() {
        mainViewModel.getCurrentWeatherData().observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Status.IDLE -> Unit
                Status.LOADING -> Unit
                Status.ERROR -> Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                Status.SUCCESS -> {
                    val currentWeather = it.data as CurrentWeather

                    // date
                    val date = formatDateFromUnixTime(currentWeather.time + currentWeather.timezoneOffset.toLong())

                    mCityTv.text = getCityFromTimezone(currentWeather.timezone)
                    mDateTv.text = date
                    mDescriptionTv.text = currentWeather.description
                    mCurrentTemperatureTv.text = getString(R.string.temperature, currentWeather.temperature.toString())
                    mFeelsLikeTempTv.text = getString(R.string.temperature, currentWeather.feelsLikeTemperature.toString())
                    mWindSpeedTv.text = getString(R.string.wind_speed, currentWeather.windSpeed.toString())
                    mUvIndexTv.text = currentWeather.ultraVioletIndex.toString()
                    mHumidityTv.text = StringBuilder(currentWeather.humidity.toString()).append("%").toString()

                    Picasso.get().load("https://openweathermap.org/img/wn/${currentWeather.icon}@2x.png")
                        .into(mIcon)

                }
            }
        })
    }
}