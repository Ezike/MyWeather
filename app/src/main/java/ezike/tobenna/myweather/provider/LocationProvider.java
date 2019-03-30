package ezike.tobenna.myweather.provider;

import ezike.tobenna.myweather.data.model.WeatherLocation;

public interface LocationProvider {

    boolean isLocationChanged(WeatherLocation location);

    String getPreferredLocationString();
}
