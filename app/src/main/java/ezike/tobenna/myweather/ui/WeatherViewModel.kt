package ezike.tobenna.myweather.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import ezike.tobenna.myweather.data.Resource
import ezike.tobenna.myweather.data.model.WeatherResponse
import ezike.tobenna.myweather.repository.Repository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.channels.ConflatedBroadcastChannel
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@FlowPreview
@ExperimentalCoroutinesApi
class WeatherViewModel @Inject constructor(repository: Repository)
    : ViewModel(), Repository by repository {

    private val channel = ConflatedBroadcastChannel<Resource<WeatherResponse>>(Resource.Loading())
    val weatherLiveData: LiveData<Resource<WeatherResponse>> = channel.asFlow().asLiveData()

    init {
        fetchData()
    }

    fun fetchData() {
        fetchWeather()
                .onEach {
                    channel.offer(it)
                }.catch {
                    channel.offer(Resource.Error(it))
                }.launchIn(viewModelScope)
    }
}