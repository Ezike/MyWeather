package ezike.tobenna.myweather.data.network;

import javax.inject.Inject;
import javax.inject.Singleton;

import androidx.lifecycle.LiveData;
import ezike.tobenna.myweather.data.model.WeatherResponse;
import ezike.tobenna.myweather.data.network.api.ApiResponse;
import ezike.tobenna.myweather.data.network.api.ApiService;
import ezike.tobenna.myweather.utils.AppExecutors;

@Singleton
public class RemoteDataSource {

    private final AppExecutors mExecutors;

    private final ApiService mApiService;

    @Inject
    public RemoteDataSource(ApiService service,
                            AppExecutors executors) {
        mApiService = service;
        mExecutors = executors;
    }

    public LiveData<ApiResponse<WeatherResponse>> fetchCurrentWeather() {
        return mApiService.getWeather("France", 1, "en");
    }
}
