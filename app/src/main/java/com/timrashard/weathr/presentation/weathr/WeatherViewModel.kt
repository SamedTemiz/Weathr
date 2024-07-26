package com.timrashard.weathr.presentation.weathr

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.timrashard.weathr.BuildConfig
import com.timrashard.weathr.common.Constant.ASSETS_BASE_URL
import com.timrashard.weathr.common.Resource
import com.timrashard.weathr.data.model.WeatherDataResult
import com.timrashard.weathr.domain.repository.ConnectivityRepository
import com.timrashard.weathr.domain.repository.WeatherRepository
import com.timrashard.weathr.utils.DataUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val repository: WeatherRepository,
    private val connectivityRepository: ConnectivityRepository
) : ViewModel() {

    private val _networkStatus = MutableStateFlow(ConnectivityRepository.NetworkStatus.Unknown)
    val networkStatus: StateFlow<ConnectivityRepository.NetworkStatus> = _networkStatus

    private val _weatherData = MutableStateFlow<Resource<WeatherDataResult>>(Resource.Loading())
    val weatherData: StateFlow<Resource<WeatherDataResult>> = _weatherData

    private val _iconUrl = MutableStateFlow<String?>(null)
    val iconUrl: StateFlow<String?> = _iconUrl

    init {
        viewModelScope.launch {
            connectivityRepository.observe().collect{ status ->
                Log.d("WeatherViewModel", "Network status: $status")
                _networkStatus.value = status
            }
        }
    }

    fun fetchWeatherData(latitude: Any, longitude: Any) {
        viewModelScope.launch {
            repository.getWeatherData(latitude, longitude, BuildConfig.API_KEY).collect { resource ->
                _weatherData.value = resource
            }
        }
    }

    fun loadIcon(iconName: String) {
        val url = "$ASSETS_BASE_URL$iconName.png"
        _iconUrl.value = url
    }

    fun fetchWeatherDataFromExample() {
        viewModelScope.launch {
            DataUtils.fetchWeatherDataFromJson(_weatherData)
        }
    }

    fun retryCheckNetworkStatus() {
        viewModelScope.launch {
            _networkStatus.value = connectivityRepository.checkCurrentStatus()
        }
    }
}