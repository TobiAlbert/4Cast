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
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.tobidaada.a4cast.R
import com.tobidaada.a4cast.databinding.FragmentWeeklyWeatherBinding
import com.tobidaada.a4cast.presentation.MainViewModel
import com.tobidaada.a4cast.presentation.adapters.ForecastAdapter
import com.tobidaada.a4cast.presentation.models.CurrentWeather
import com.tobidaada.a4cast.presentation.models.Forecast
import com.tobidaada.a4cast.presentation.models.Status
import com.tobidaada.a4cast.utils.formatDateFromUnixTime
import com.tobidaada.a4cast.utils.getCityFromTimezone
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class WeeklyWeatherFragment : Fragment(R.layout.fragment_weekly_weather) {

    @Inject lateinit var mAdapter: ForecastAdapter
    private val mainViewModel: MainViewModel by viewModels()
    private var _binding: FragmentWeeklyWeatherBinding? = null
    private val binding: FragmentWeeklyWeatherBinding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWeeklyWeatherBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUi(view)
        observeViewModel()
    }

    private fun setupUi(view: View) {
        binding.forecastRv.apply {
            adapter = mAdapter
            layoutManager = LinearLayoutManager(requireContext())
            addItemDecoration(DividerItemDecoration(requireContext(), RecyclerView.VERTICAL))
        }
    }

    private fun observeViewModel() {
        mainViewModel.getForecast().observe(viewLifecycleOwner, Observer {
            it?.let {
                when(it.status) {
                    Status.IDLE -> Unit
                    Status.LOADING -> Unit
                    Status.ERROR -> Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                    Status.SUCCESS -> {
                        val forecasts = it.data as List<Forecast>

                        // skip the first item
                        val nextSevenDays = forecasts.takeLast(forecasts.size - 1)

                        mAdapter.addItems(nextSevenDays)
                    }
                }
            }
        })

        mainViewModel.getCurrentWeatherData().observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Status.IDLE -> Unit
                Status.LOADING -> Unit
                Status.ERROR -> Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                Status.SUCCESS -> {
                    val currentWeather = it.data as CurrentWeather

                    // date
                    val date = formatDateFromUnixTime(currentWeather.time + currentWeather.timezoneOffset.toLong())
                    binding.city.text = getCityFromTimezone(currentWeather.timezone)
                    binding.date.text = date
                    binding.description.text = currentWeather.description
                    binding.currentTemperature.text = getString(R.string.temperature, currentWeather.temperature.toString())


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