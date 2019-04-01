package ezike.tobenna.myweather.data.local;

import android.annotation.TargetApi;
import android.os.Build;

import org.threeten.bp.ZonedDateTime;

import javax.inject.Inject;
import javax.inject.Singleton;

import androidx.lifecycle.LiveData;
import ezike.tobenna.myweather.data.local.dao.WeatherDao;
import ezike.tobenna.myweather.data.local.entity.WeatherResponse;
import ezike.tobenna.myweather.data.model.WeatherLocation;
import ezike.tobenna.myweather.provider.LocationProvider;

@Singleton
public class LocalDataSource {

    private final WeatherDao mWeatherDao;

    private LocationProvider mLocationProvider;

    @Inject
    public LocalDataSource(LocationProvider locationProvider,
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

    public boolean hasLocationChanged(WeatherLocation location) {
        return mLocationProvider.isLocationChanged(location);
    }

    @TargetApi(Build.VERSION_CODES.O)
    public boolean shouldFetch(ZonedDateTime lastFetchTime) {
        ZonedDateTime timeElapsed = ZonedDateTime.now().minusMinutes(30);
        return lastFetchTime.isBefore(timeElapsed);
    }
}
