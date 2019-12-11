package ezike.tobenna.myweather.repository

import android.content.Context
import android.content.SharedPreferences
import ezike.tobenna.myweather.data.Resource
import ezike.tobenna.myweather.data.local.LocalDataSource
import ezike.tobenna.myweather.data.model.WeatherResponse
import ezike.tobenna.myweather.data.remote.RemoteSource
import ezike.tobenna.myweather.provider.LocationProvider
import ezike.tobenna.myweather.widget.WeatherWidgetProvider
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

class WeatherRepository @Inject constructor(
        private val dispatcher: CoroutineDispatcher,
        private val remoteSource: RemoteSource,
        private val localDataSource: LocalDataSource,
        private val locationProvider: LocationProvider,
        private val sharedPreferences: SharedPreferences,
        private val context: Context
) : Repository {

    @ExperimentalCoroutinesApi
    override fun fetchWeather(): Flow<Resource<WeatherResponse>> {
        return flow {
            val initial: WeatherResponse = localDataSource.getWeather()
            emit(Resource.Loading(data = initial))
            val weather: WeatherResponse = remoteSource.fetchWeather(locationProvider.preferredLocationString)
            localDataSource.save(weather)
            val savedData: WeatherResponse = localDataSource.getWeather()
            updateWidgetData(savedData)
            emit(Resource.Success(data = savedData))
        }.catch {
            emit(Resource.Error(it))
            it.printStackTrace()
        }.flowOn(dispatcher)
    }

    private suspend fun updateWidgetData(weather: WeatherResponse) {
        saveToPreferences(weather)
        WeatherWidgetProvider.updateWidget(context)
    }

    private suspend fun saveToPreferences(weather: WeatherResponse) {
        coroutineScope {
            launch {
                val editor = sharedPreferences.edit()
                if (weather.current.weatherDescriptions.isNotEmpty()) {
                    editor.putString(WIDGET_TEXT, weather.current.weatherDescriptions[0])
                    editor.putString(WIDGET_LOCATION, weather.weatherLocation.region)
                    editor.putString(WIDGET_ICON, weather.current.weatherDescriptions[0])
                    editor.apply()
                }
            }
        }
    }

    companion object {
        const val WIDGET_TEXT: String = "ezike.tobenna.myweather.ui.widget.text"
        const val WIDGET_LOCATION: String = "ezike.tobenna.myweather.ui.widget.location"
        const val WIDGET_ICON: String = "ezike.tobenna.myweather.ui.widget.icon"
    }
}