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
import ezike.tobenna.myweather.data.local.units.UnitSpecificWeather;
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

    private LiveData<UnitSpecificWeather> unitSpecificWeatherLiveData;

    @Inject
    WeatherRepository(AppExecutors appExecutors,
                      RemoteDataSource remoteDataSource,
                      LocalDataSource localDataSource) {
        mRemoteDataSource = remoteDataSource;
        mExecutors = appExecutors;
        mLocalDataSource = localDataSource;
    }

    public LiveData<Resource<WeatherResponse>> loadWeatherResponse() {
        return new NetworkBoundResource<WeatherResponse, WeatherResponse>(mExecutors) {

            @Override
            protected void saveCallResult(@NonNull WeatherResponse item) {
                mLocalDataSource.saveResponse(item);
                Timber.d("Weather response saved");

                mLocalDataSource.saveWeather(item);
                Timber.d("Weather saved to database");

            }

            @Override
            protected boolean shouldFetch(@Nullable WeatherResponse data) {
                if (data != null) {
                    WeatherLocation location = data.getLocation();
                    ZonedDateTime timeElapsed = location.getZonedDateTime();
                    Timber.d("LOCATION %s", location.getCountry());
                    return mLocalDataSource.shouldFetch(timeElapsed) || mLocalDataSource.hasLocationChanged(location);
                }
                return data == null || rateLimit.shouldFetch(data.getCurrent().getLastUpdated());
            }

            @NonNull
            @Override
            protected LiveData<WeatherResponse> loadFromDb() {
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
            }
        }.asLiveData();
    }

//    public LiveData<UnitSpecificWeather> getUnitWeather(boolean metric) {
//        if (loadWeather() != null) {
//            try {
//                unitSpecificWeatherLiveData = mLocalDataSource.getUnitSpecificWeather(metric);
//                Timber.d("Get unit weather success");
//            } catch (Exception e) {
//                e.printStackTrace();
//                Timber.d(e, "Get unit weather failed");
//            }
//
//        } else {
//            Timber.d("Get unit weather failed");
//            return null;
//        }
//
//        return unitSpecificWeatherLiveData;
//    }
}

