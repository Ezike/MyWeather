package ezike.tobenna.myweather.repository

import android.content.Context
import android.content.SharedPreferences
import ezike.tobenna.myweather.AppCoroutineDispatchers
import ezike.tobenna.myweather.data.Resource
import ezike.tobenna.myweather.data.local.LocalDataSource
import ezike.tobenna.myweather.data.model.WeatherResponse
import ezike.tobenna.myweather.data.remote.RemoteSource
import ezike.tobenna.myweather.provider.LocationProvider
import ezike.tobenna.myweather.widget.WeatherWidgetProvider
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@ExperimentalCoroutinesApi
class WeatherRepository @Inject constructor(
        private val dispatcher: AppCoroutineDispatchers,
        private val remoteSource: RemoteSource,
        private val localDataSource: LocalDataSource,
        private val locationProvider: LocationProvider,
        private val sharedPreferences: SharedPreferences,
        private val context: Context
) : Repository {

    @ExperimentalCoroutinesApi
    override fun fetchWeather(): Flow<Resource<WeatherResponse>> {
        return flow {
            val currentData: WeatherResponse = localDataSource.getWeather().first()
            emit(Resource.Loading(currentData))
            fetchWeatherAndCache()
            emitAll(localDataSource.getWeather().map { Resource.Success(it) })
        }.catch {
            val previousData: WeatherResponse = localDataSource.getWeather().first()
            emit(Resource.Error(it, previousData))
            it.printStackTrace()
        }.flowOn(dispatcher.io)
    }

    private fun updateWidgetData(weather: WeatherResponse) {
        saveToPreferences(weather)
        WeatherWidgetProvider.updateWidget(context)
    }

    private suspend fun fetchWeatherAndCache() {
        val weather: WeatherResponse = remoteSource
                .fetchWeather(locationProvider.preferredLocationString)
        localDataSource.save(weather)
        updateWidgetData(weather)
    }

    private fun saveToPreferences(weather: WeatherResponse) {
        val editor = sharedPreferences.edit()
        if (weather.current.weatherDescriptions.isNotEmpty()) {
            editor.putString(WIDGET_TEXT, weather.current.weatherDescriptions[0])
            editor.putString(WIDGET_LOCATION, weather.weatherLocation.region)
            editor.putString(WIDGET_ICON, weather.current.weatherDescriptions[0])
            editor.apply()
        }
    }

    companion object {
        const val WIDGET_TEXT: String = "ezike.tobenna.myweather.ui.widget.text"
        const val WIDGET_LOCATION: String = "ezike.tobenna.myweather.ui.widget.location"
        const val WIDGET_ICON: String = "ezike.tobenna.myweather.ui.widget.icon"
    }
}