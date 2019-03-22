package ezike.tobenna.myweather.data.local.units;

/**
 * @author tobennaezike
 */
public interface UnitSpecificWeather {
    double getTemperature();

    String getConditionText();

    String getConditionIconUrl();

    double getWindSpeed();

    double getPrecipitationVolume();

    double getFeelsLikeTemperature();

    double getVisibilityDistance();
}
