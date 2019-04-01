package ezike.tobenna.myweather.ui;

import javax.inject.Inject;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;
import ezike.tobenna.myweather.data.local.entity.WeatherResponse;
import ezike.tobenna.myweather.repository.WeatherRepository;
import ezike.tobenna.myweather.utils.Resource;

public class CurrentWeatherViewModel extends ViewModel {

    private WeatherRepository mWeatherRepository;

    private MutableLiveData<String> value = new MutableLiveData<>();

    private LiveData<Resource<WeatherResponse>> mWeather;

    @Inject
    CurrentWeatherViewModel(WeatherRepository repository) {
        mWeatherRepository = repository;
        mWeather = Transformations.switchMap(value, input -> mWeatherRepository.loadWeatherResponse(input));
        setRefreshId(value.getValue());
    }

    LiveData<Resource<WeatherResponse>> getCurrentWeather() {
        return mWeather;
    }

    void retry(String input) {
        setRefreshId(input);
    }

    private void setRefreshId(String input) {
        value.setValue(input);
    }
}
