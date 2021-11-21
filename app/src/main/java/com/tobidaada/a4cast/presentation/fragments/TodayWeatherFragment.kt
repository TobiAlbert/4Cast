package com.tobidaada.a4cast.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.squareup.picasso.Picasso
import com.tobidaada.a4cast.R
import com.tobidaada.a4cast.databinding.FragmentTodayWeatherBinding
import com.tobidaada.a4cast.presentation.MainViewModel
import com.tobidaada.a4cast.presentation.models.CurrentWeather
import com.tobidaada.a4cast.presentation.models.Status
import com.tobidaada.a4cast.utils.formatDateFromUnixTime
import com.tobidaada.a4cast.utils.getCityFromTimezone
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TodayWeatherFragment : Fragment() {

    private val mainViewModel: MainViewModel by viewModels()
    private var _binding: FragmentTodayWeatherBinding? = null
    private val binding: FragmentTodayWeatherBinding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTodayWeatherBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeViewModel()
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

                    binding.cityName.text = getCityFromTimezone(currentWeather.timezone)
                    binding.date.text = date
                    binding.description.text = currentWeather.description
                    binding.currentTemperature.text = getString(R.string.temperature, currentWeather.temperature.toString())
                    binding.feelsLikeTemp.text = getString(R.string.temperature, currentWeather.feelsLikeTemperature.toString())
                    binding.windSpeed.text = getString(R.string.wind_speed, currentWeather.windSpeed.toString())
                    binding.uvIndex.text = currentWeather.ultraVioletIndex.toString()
                    binding.humidity.text = StringBuilder(currentWeather.humidity.toString()).append("%").toString()

                    Picasso.get().load("https://openweathermap.org/img/wn/${currentWeather.icon}@2x.png")
                        .into(binding.icon)

                }
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}