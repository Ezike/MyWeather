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

    private MutableLiveData<String> value = new MutableLiveData<>();

    private LiveData<Resource<WeatherResponse>> mWeather;

    @Inject
    CurrentWeatherViewModel(WeatherRepository repository) {
        mWeather = Transformations.switchMap(value, repository::loadWeatherResponse);
        setRefreshId(value.getValue());
    }

    public LiveData<Resource<WeatherResponse>> getCurrentWeather() {
        return mWeather;
    }

    public void retry(String input) {
        setRefreshId(input);
    }

    private void setRefreshId(String input) {
        value.setValue(input);
    }
}
