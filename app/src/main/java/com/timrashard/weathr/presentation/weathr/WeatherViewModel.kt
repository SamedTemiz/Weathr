package com.timrashard.weathr.presentation.weathr

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.timrashard.weathr.BuildConfig
import com.timrashard.weathr.common.Constant.ASSETS_BASE_URL
import com.timrashard.weathr.common.Resource
import com.timrashard.weathr.data.model.WeatherDataResult
import com.timrashard.weathr.data.model.air.AirPollutionDataResult
import com.timrashard.weathr.domain.repository.AirRepository
import com.timrashard.weathr.domain.repository.ConnectivityRepository
import com.timrashard.weathr.domain.repository.WeatherRepository
import com.timrashard.weathr.utils.DataUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val weatherRepository: WeatherRepository,
    private val airRepository: AirRepository,
    private val connectivityRepository: ConnectivityRepository
) : ViewModel() {

    private val _networkStatus = MutableStateFlow(ConnectivityRepository.NetworkStatus.Unknown)
    val networkStatus: StateFlow<ConnectivityRepository.NetworkStatus> = _networkStatus

    private val _weatherData = MutableStateFlow<Resource<WeatherDataResult>>(Resource.Loading())
    val weatherData: StateFlow<Resource<WeatherDataResult>> = _weatherData

    private val _airData = MutableStateFlow<Resource<AirPollutionDataResult>>(Resource.Loading())
    val airData: StateFlow<Resource<AirPollutionDataResult>> = _airData

    private val _iconUrl = MutableStateFlow<String?>(null)
    val iconUrl: StateFlow<String?> = _iconUrl

    init {
        viewModelScope.launch {
            connectivityRepository.observe().collect { status ->
                Log.d("WeatherViewModel", "Network status: $status")
                _networkStatus.value = status
            }
        }
    }

    fun fetchWeatherData(latitude: Any, longitude: Any) {
        viewModelScope.launch {
            weatherRepository.getWeatherData(
                latitude = latitude,
                longitude = longitude,
                apiKey = BuildConfig.WEATHER_API_KEY
            ).collect { resource ->
                _weatherData.value = resource
            }
        }
    }

    fun fetchAirPollutionData() {
        viewModelScope.launch {
            airRepository.getAirPollutionData(
                latitude = 38.395186,
                longitude = 27.076178,
                start = 1722075729,
                end = 1722172929,
                apiKey = BuildConfig.AIR_POLLUTION_API_KEY
            ).collect { resource ->
                _airData.value = resource
            }
        }
    }

    fun exampleAirPollutionData() {
        viewModelScope.launch {
            DataUtils.fetchAirPollutionDataFromJson(_airData)
        }
    }

    fun loadIcon(iconName: String) {
        val url = "$ASSETS_BASE_URL$iconName.png"
        _iconUrl.value = url
    }

//    fun fetchWeatherDataFromExample() {
//        viewModelScope.launch {
//            DataUtils.fetchWeatherDataFromJson(_weatherData)
//        }
//    }
}