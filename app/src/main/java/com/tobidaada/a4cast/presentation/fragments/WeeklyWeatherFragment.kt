package com.tobidaada.a4cast.presentation.fragments

import android.os.Bundle
import android.view.View
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

    private lateinit var mCityTv: TextView
    private lateinit var mDateTv: TextView
    private lateinit var mDescriptionTv: TextView
    private lateinit var mCurrentTemperatureTv: TextView
    private lateinit var mRecyclerView: RecyclerView
    private lateinit var mIconImageView: ImageView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUi(view)
        observeViewModel()
    }

    private fun setupUi(view: View) {
        mCityTv = view.findViewById(R.id.city)
        mDateTv = view.findViewById(R.id.date)
        mDescriptionTv = view.findViewById(R.id.description)
        mCurrentTemperatureTv = view.findViewById(R.id.currentTemperature)
        mRecyclerView = view.findViewById(R.id.forecastRv)
        mIconImageView = view.findViewById(R.id.icon)

        mRecyclerView.apply {
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
                    mCityTv.text = getCityFromTimezone(currentWeather.timezone)
                    mDateTv.text = date
                    mDescriptionTv.text = currentWeather.description
                    mCurrentTemperatureTv.text = getString(R.string.temperature, currentWeather.temperature.toString())


                    Picasso.get().load("https://openweathermap.org/img/wn/${currentWeather.icon}@2x.png")
                        .into(mIconImageView)
                }
            }
        })
    }
}