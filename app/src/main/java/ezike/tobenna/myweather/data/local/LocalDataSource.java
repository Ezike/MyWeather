package ezike.tobenna.myweather.data.local;

import android.annotation.TargetApi;
import android.os.Build;

import org.threeten.bp.ZonedDateTime;

import javax.inject.Inject;
import javax.inject.Singleton;

import androidx.lifecycle.LiveData;
import ezike.tobenna.myweather.data.local.dao.WeatherDao;
import ezike.tobenna.myweather.data.local.entity.WeatherResponse;
import ezike.tobenna.myweather.provider.LocationProvider;

@Singleton
public class LocalDataSource {

    private final WeatherDao mWeatherDao;

    private LocationProvider mLocationProvider;

    @Inject
    LocalDataSource(LocationProvider locationProvider,
                    WeatherDao weatherDao) {
        mLocationProvider = locationProvider;
        mWeatherDao = weatherDao;
    }

    public void saveResponse(WeatherResponse response) {
        mWeatherDao.insertWeatherResponse(response);
    }

    public LiveData<WeatherResponse> getWeatherResponse() {
        return mWeatherDao.getWeatherResponse();
    }

    public boolean hasLocationChanged(WeatherResponse response) {
        return mLocationProvider.isLocationChanged(response.getLocation());
    }

    @TargetApi(Build.VERSION_CODES.O)
    public boolean shouldFetch(WeatherResponse response) {
        ZonedDateTime locationTime = response.getLocation().getZonedDateTime();
        ZonedDateTime timeElapsed = ZonedDateTime.now().minusMinutes(30);
        return locationTime.isBefore(timeElapsed);
    }
}
