package ezike.tobenna.myweather.repository;

import org.threeten.bp.ZonedDateTime;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;
import javax.inject.Singleton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import ezike.tobenna.myweather.data.NetworkBoundResource;
import ezike.tobenna.myweather.data.local.LocalDataSource;
import ezike.tobenna.myweather.data.local.entity.WeatherResponse;
import ezike.tobenna.myweather.data.model.WeatherLocation;
import ezike.tobenna.myweather.data.remote.RemoteDataSource;
import ezike.tobenna.myweather.data.remote.api.ApiResponse;
import ezike.tobenna.myweather.utils.AppExecutors;
import ezike.tobenna.myweather.utils.RateLimiter;
import ezike.tobenna.myweather.utils.Resource;
import timber.log.Timber;

@Singleton
public class WeatherRepository {

    private final RemoteDataSource mRemoteDataSource;

    private final AppExecutors mExecutors;

    private final LocalDataSource mLocalDataSource;

    private RateLimiter<String> rateLimit = new RateLimiter<>(30, TimeUnit.MINUTES);


    @Inject
    WeatherRepository(AppExecutors appExecutors,
                      RemoteDataSource remoteDataSource,
                      LocalDataSource localDataSource) {
        mRemoteDataSource = remoteDataSource;
        mExecutors = appExecutors;
        mLocalDataSource = localDataSource;
    }

    public LiveData<Resource<WeatherResponse>> loadWeatherResponse(String input) {
        return new NetworkBoundResource<WeatherResponse, WeatherResponse>(mExecutors) {
            @Override
            protected void saveCallResult(@NonNull WeatherResponse item) {
                mLocalDataSource.saveResponse(item);
                Timber.d("Weather response saved");
            }

            @Override
            protected boolean shouldFetch(@Nullable WeatherResponse data) {
                if (data != null) {
                    WeatherLocation location = data.getLocation();
                    ZonedDateTime timeElapsed = location.getZonedDateTime();
                    Timber.d("LOCATION %s", location.getCountry());
                    return mLocalDataSource.shouldFetch(timeElapsed) || mLocalDataSource.hasLocationChanged(location);
                }
                return data == null || rateLimit.shouldFetch(input);
            }

            @NonNull
            @Override
            protected LiveData<WeatherResponse> loadFromDb() {
                Timber.d("loading Weather data from database");
                return mLocalDataSource.getWeatherResponse();
            }

            @NonNull
            @Override
            protected LiveData<ApiResponse<WeatherResponse>> createCall() {
                mRemoteDataSource.startWeatherFetch();
                Timber.d("Weather data fetch started");
                return mRemoteDataSource.fetchWeather();
            }

            @Override
            protected void onFetchFailed() {
                Timber.d("Fetch failed!!");
                rateLimit.reset(input);
            }
        }.asLiveData();
    }
}

