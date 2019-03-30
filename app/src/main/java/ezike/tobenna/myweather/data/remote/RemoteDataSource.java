package ezike.tobenna.myweather.data.remote;

import android.content.Context;

import java.util.Locale;

import javax.inject.Inject;
import javax.inject.Singleton;

import androidx.lifecycle.LiveData;
import ezike.tobenna.myweather.data.local.entity.WeatherResponse;
import ezike.tobenna.myweather.data.remote.api.ApiResponse;
import ezike.tobenna.myweather.data.remote.api.ApiService;
import ezike.tobenna.myweather.provider.LocationProvider;

@Singleton
public class RemoteDataSource {

    private static final int DAYS = 1;

    private final ApiService mApiService;

    private final LocationProvider mLocationProvider;

    private Context mContext;

    @Inject
    public RemoteDataSource(ApiService service,
                            LocationProvider locationProvider, Context context) {
        mApiService = service;
        mLocationProvider = locationProvider;
        mContext = context;
    }

    public LiveData<ApiResponse<WeatherResponse>> fetchWeather() {
        return mApiService.getWeather(mLocationProvider.getPreferredLocationString(), DAYS, Locale.getDefault().getLanguage());
    }

    public void startWeatherFetch() {
        WeatherFetchService.startActionFetchWeather(mContext);
    }
}
