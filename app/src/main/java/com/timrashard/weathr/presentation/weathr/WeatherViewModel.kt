package com.timrashard.weathr.presentation.weathr

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.timrashard.weathr.common.Resource
import com.timrashard.weathr.data.model.WeatherDataResult
import com.timrashard.weathr.domain.repository.WeatherRepository
import com.timrashard.weathr.utils.DataUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val repository: WeatherRepository
) : ViewModel() {

    private val _weatherData = MutableStateFlow<Resource<WeatherDataResult>>(Resource.Loading())
    val weatherData: StateFlow<Resource<WeatherDataResult>> = _weatherData

    fun fetchWeatherData(latitude: Double, longitude: Double, apiKey: String) {
        viewModelScope.launch {
            repository.getWeatherData(latitude, longitude, apiKey).collect { resource ->
                _weatherData.value = resource
            }
        }
    }

    fun fetchWeatherDataFromExample() {
        viewModelScope.launch {
            DataUtils.fetchWeatherDataFromJson(_weatherData)
        }
    }
}