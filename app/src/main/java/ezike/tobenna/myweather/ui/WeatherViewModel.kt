package ezike.tobenna.myweather.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ezike.tobenna.myweather.data.Resource
import ezike.tobenna.myweather.data.model.WeatherResponse
import ezike.tobenna.myweather.repository.Repository
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class WeatherViewModel @Inject constructor(private val repository: Repository)
    : ViewModel() {

    private val _weatherLiveData = MutableLiveData<Resource<WeatherResponse>>()
    val weatherLiveData: LiveData<Resource<WeatherResponse>> = _weatherLiveData

    init {
        fetchData()
    }

    fun fetchData() {
        viewModelScope.launch {
            repository.fetchWeather().collect {
                _weatherLiveData.value = it
            }
        }
    }
}