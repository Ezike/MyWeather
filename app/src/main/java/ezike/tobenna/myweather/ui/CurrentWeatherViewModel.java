package ezike.tobenna.myweather.ui;

import javax.inject.Inject;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import ezike.tobenna.myweather.data.Resource;
import ezike.tobenna.myweather.data.WeatherRepository;
import ezike.tobenna.myweather.data.local.entity.CurrentWeather;
import timber.log.Timber;

public class CurrentWeatherViewModel extends ViewModel {

    private WeatherRepository mWeatherRepository;
    private LiveData<Resource<CurrentWeather>> weather;

    @Inject
    CurrentWeatherViewModel(WeatherRepository repository) {
        mWeatherRepository = repository;
    }

    public void init() {
        if (weather != null) {
            return;
        }
        Timber.d("Initializing viewModel");
        weather = mWeatherRepository.loadWeather();
    }


    LiveData<Resource<CurrentWeather>> getCurrentWeather() {
        return weather;
    }
}
