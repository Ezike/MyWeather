package ezike.tobenna.myweather.data.local;

import javax.inject.Inject;
import javax.inject.Singleton;

import androidx.lifecycle.LiveData;
import ezike.tobenna.myweather.data.local.dao.CurrentWeatherDao;
import ezike.tobenna.myweather.data.local.entity.CurrentWeather;
import ezike.tobenna.myweather.data.model.WeatherResponse;

@Singleton
public class LocalDataSource {

    private final CurrentWeatherDao mCurrentWeatherDao;

    @Inject
    private LocalDataSource(CurrentWeatherDao currentWeatherDao) {
        mCurrentWeatherDao = currentWeatherDao;
    }

    public void saveWeather(WeatherResponse response) {
        mCurrentWeatherDao.insertCurrentWeather(response.getCurrent());
    }

    public LiveData<CurrentWeather> getCurrentWeather() {
        return mCurrentWeatherDao.getImperialWeather();
    }
}
