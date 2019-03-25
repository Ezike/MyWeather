package ezike.tobenna.myweather.provider;

import ezike.tobenna.myweather.data.local.entity.WeatherLocation;

public interface LocationProvider {
    boolean isLocationChanged(WeatherLocation location);

    String getPreferredLocationString();
}
