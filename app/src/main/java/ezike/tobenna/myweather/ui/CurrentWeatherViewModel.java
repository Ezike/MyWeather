package ezike.tobenna.myweather.ui;

import javax.inject.Inject;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import ezike.tobenna.myweather.data.local.entity.WeatherResponse;
import ezike.tobenna.myweather.repository.WeatherRepository;
import ezike.tobenna.myweather.utils.Resource;

public class CurrentWeatherViewModel extends ViewModel {

    private LiveData<Resource<WeatherResponse>> mWeather;

    @Inject
    CurrentWeatherViewModel(WeatherRepository repository) {
        mWeather = repository.loadWeatherResponse();
    }

    LiveData<Resource<WeatherResponse>> getCurrentWeather() {
        return mWeather;
    }

}
