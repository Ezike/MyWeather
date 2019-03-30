package ezike.tobenna.myweather.data.local;

import android.annotation.TargetApi;
import android.os.Build;

import org.threeten.bp.ZonedDateTime;

import javax.inject.Inject;
import javax.inject.Singleton;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import ezike.tobenna.myweather.data.local.dao.CurrentWeatherDao;
import ezike.tobenna.myweather.data.local.dao.WeatherDao;
import ezike.tobenna.myweather.data.local.entity.WeatherResponse;
import ezike.tobenna.myweather.data.local.units.UnitSpecificWeather;
import ezike.tobenna.myweather.data.model.WeatherLocation;
import ezike.tobenna.myweather.provider.LocationProvider;
import ezike.tobenna.myweather.utils.AppExecutors;

@Singleton
public class LocalDataSource {

    private final CurrentWeatherDao mCurrentWeatherDao;

    private final WeatherDao mWeatherDao;

    private LocationProvider mLocationProvider;

    private AppExecutors mExecutors;

    private MutableLiveData<UnitSpecificWeather> mUnitSpecificWeatherMutableLiveData = new MutableLiveData<>();

    @Inject
    public LocalDataSource(CurrentWeatherDao currentWeatherDao, LocationProvider locationProvider,
                           AppExecutors appExecutors, WeatherDao weatherDao) {
        mCurrentWeatherDao = currentWeatherDao;
        mLocationProvider = locationProvider;
        mExecutors = appExecutors;
        mWeatherDao = weatherDao;
    }

    public void saveResponse(WeatherResponse response) {
        mWeatherDao.insertWeatherResponse(response);
    }

    public void saveWeather(WeatherResponse response) {
        mCurrentWeatherDao.insertCurrentWeather(response.getCurrent());
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

    public LiveData<UnitSpecificWeather> getUnitSpecificWeather(boolean metric) {
        if (metric) {
            mExecutors.diskIO().execute(() ->
                    mUnitSpecificWeatherMutableLiveData.postValue(mCurrentWeatherDao.getMetricWeather().getValue()));

        } else {
            mExecutors.diskIO().execute(() ->
                    mUnitSpecificWeatherMutableLiveData.postValue(mCurrentWeatherDao.getImperialWeather().getValue()));
        }

        return mUnitSpecificWeatherMutableLiveData;
    }

}
