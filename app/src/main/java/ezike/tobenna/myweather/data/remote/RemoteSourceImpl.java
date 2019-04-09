package ezike.tobenna.myweather.data.remote;

import java.util.Locale;

import javax.inject.Inject;

import androidx.lifecycle.LiveData;
import ezike.tobenna.myweather.data.local.entity.WeatherResponse;
import ezike.tobenna.myweather.data.remote.api.ApiResponse;
import ezike.tobenna.myweather.data.remote.api.ApiService;
import ezike.tobenna.myweather.data.source.BaseSource;
import ezike.tobenna.myweather.provider.LocationProvider;

public class RemoteSourceImpl implements BaseSource<LiveData<ApiResponse<WeatherResponse>>> {

    private static final int DAYS = 1;

    private final ApiService mApiService;

    private final LocationProvider mLocationProvider;

    @Inject
    RemoteSourceImpl(ApiService service,
                     LocationProvider locationProvider) {
        mApiService = service;
        mLocationProvider = locationProvider;
    }

    @Override
    public LiveData<ApiResponse<WeatherResponse>> get() {
        return mApiService.getWeather(mLocationProvider.getPreferredLocationString(), DAYS, Locale.getDefault().getLanguage());
    }
}
