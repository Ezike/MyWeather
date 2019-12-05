package ezike.tobenna.myweather.provider;

import org.jetbrains.annotations.NotNull;

import ezike.tobenna.myweather.data.model.WeatherLocation;

public interface LocationProvider {

    boolean isLocationChanged(WeatherLocation weatherLocation);

    @NotNull  String getPreferredLocationString();
}
