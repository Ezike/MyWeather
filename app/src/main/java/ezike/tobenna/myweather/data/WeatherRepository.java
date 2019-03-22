package ezike.tobenna.myweather.data;

import javax.inject.Inject;
import javax.inject.Singleton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import ezike.tobenna.myweather.data.local.CurrentWeatherDao;
import ezike.tobenna.myweather.data.local.entity.CurrentWeather;
import ezike.tobenna.myweather.data.model.WeatherResponse;
import ezike.tobenna.myweather.data.network.api.ApiResponse;
import ezike.tobenna.myweather.data.network.api.ApiService;
import ezike.tobenna.myweather.utils.AppExecutors;
import timber.log.Timber;

@Singleton
public class WeatherRepository {

    private final ApiService mApiService;

    private final AppExecutors mExecutors;

    private final CurrentWeatherDao mCurrentWeatherDao;

    @Inject
    WeatherRepository(AppExecutors appExecutors,
                      ApiService apiService,
                      CurrentWeatherDao weatherDao) {
        mApiService = apiService;
        mExecutors = appExecutors;
        mCurrentWeatherDao = weatherDao;
    }

    public LiveData<Resource<CurrentWeather>> loadWeather() {
        return new NetworkBoundResource<CurrentWeather, WeatherResponse>(mExecutors) {

            @Override
            protected void saveCallResult(@NonNull WeatherResponse item) {
                long id = mCurrentWeatherDao.insertCurrentWeather(item.getCurrent());
                Timber.d("Weather with id %d saved to database", id);
            }

            @Override
            protected boolean shouldFetch(@Nullable CurrentWeather data) {
                return data == null;
            }

            @NonNull
            @Override
            protected LiveData<CurrentWeather> loadFromDb() {
                Timber.d("loading weather from database");
                return mCurrentWeatherDao.getImperialWeather();
            }

            @NonNull
            @Override
            protected LiveData<ApiResponse<WeatherResponse>> createCall() {
                Timber.d("Weather data fetch started");
                return mApiService.getWeather("France", 1, "en");
            }

            @Override
            protected void onFetchFailed() {
                Timber.d("Fetch failed!!");
            }
        }.asLiveData();
    }
}
