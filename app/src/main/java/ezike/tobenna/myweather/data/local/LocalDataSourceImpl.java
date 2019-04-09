package ezike.tobenna.myweather.data.local;

import org.threeten.bp.ZonedDateTime;

import javax.inject.Inject;

import androidx.lifecycle.LiveData;
import ezike.tobenna.myweather.data.local.dao.WeatherDao;
import ezike.tobenna.myweather.data.local.entity.WeatherResponse;
import ezike.tobenna.myweather.provider.LocationProvider;

public class LocalDataSourceImpl implements LocalDataSource<WeatherResponse, LiveData<WeatherResponse>> {

    private final WeatherDao mWeatherDao;

    private LocationProvider mLocationProvider;

    @Inject
    LocalDataSourceImpl(LocationProvider locationProvider,
                        WeatherDao weatherDao) {
        mLocationProvider = locationProvider;
        mWeatherDao = weatherDao;
    }

    @Override
    public void save(WeatherResponse response) {
        mWeatherDao.insertWeatherResponse(response);
    }

    @Override
    public boolean hasLocationChanged(WeatherResponse response) {
        return mLocationProvider.isLocationChanged(response.getLocation());
    }

    @Override
    public boolean shouldFetch(WeatherResponse response) {
        ZonedDateTime locationTime = response.getLocation().getZonedDateTime();
        ZonedDateTime timeElapsed = ZonedDateTime.now().minusMinutes(30);
        return locationTime.isBefore(timeElapsed);
    }

    @Override
    public LiveData<WeatherResponse> get() {
        return mWeatherDao.getWeatherResponse();
    }
}
