package ezike.tobenna.myweather.repository;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;
import javax.inject.Singleton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import ezike.tobenna.myweather.data.NetworkBoundResource;
import ezike.tobenna.myweather.data.local.LocalDataSource;
import ezike.tobenna.myweather.data.local.entity.WeatherResponse;
import ezike.tobenna.myweather.data.remote.api.ApiResponse;
import ezike.tobenna.myweather.data.source.BaseSource;
import ezike.tobenna.myweather.utils.AppExecutors;
import ezike.tobenna.myweather.utils.RateLimiter;
import ezike.tobenna.myweather.utils.Resource;
import timber.log.Timber;

@Singleton
public class RepoImpl implements Repo<Resource<WeatherResponse>> {

    private final LocalDataSource<WeatherResponse, LiveData<WeatherResponse>> mLocalDataSource;

    private final BaseSource<LiveData<ApiResponse<WeatherResponse>>> mBaseSource;

    private final AppExecutors mExecutors;

    private RateLimiter<String> rateLimit = new RateLimiter<>(30, TimeUnit.MINUTES);

    @Inject
    RepoImpl(AppExecutors executors, LocalDataSource dataSource, BaseSource baseSource) {
        mExecutors = executors;
        mLocalDataSource = dataSource;
        mBaseSource = baseSource;
    }

    @Override
    public LiveData<Resource<WeatherResponse>> loadData(String input) {
        return new NetworkBoundResource<WeatherResponse, WeatherResponse>(mExecutors) {

            @Override
            protected void saveCallResult(@NonNull WeatherResponse item) {
                mLocalDataSource.save(item);
                Timber.d("Weather response saved");
            }

            @Override
            protected boolean shouldFetch(@Nullable WeatherResponse data) {
                if (data != null) {
                    Timber.d("LOCATION %s", data.getLocation().getCountry());
                    return mLocalDataSource.shouldFetch(data) || mLocalDataSource.hasLocationChanged(data);
                }
                return data == null || rateLimit.shouldFetch(input);
            }

            @NonNull
            @Override
            protected LiveData<WeatherResponse> loadFromDb() {
                Timber.d("loading Weather data from database");
                return mLocalDataSource.get();
            }

            @NonNull
            @Override
            protected LiveData<ApiResponse<WeatherResponse>> createCall() {
                Timber.d("Weather data fetch started");
                return mBaseSource.get();
            }

            @Override
            protected void onFetchFailed() {
                Timber.d("Fetch failed!!");
                rateLimit.reset(input);
            }

        }.asLiveData();
    }
}
