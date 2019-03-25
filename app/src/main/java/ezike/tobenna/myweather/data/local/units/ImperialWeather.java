package ezike.tobenna.myweather.data.local.units;

import androidx.room.ColumnInfo;

public class ImperialWeather implements UnitSpecificWeather {

    @ColumnInfo(name = "tempF")
    private double temperature;
    @ColumnInfo(name = "condition_text")
    private String conditionText;
    @ColumnInfo(name = "condition_icon")
    private String conditionIconUrl;
    @ColumnInfo(name = "windMph")
    private double windSpeed;
    @ColumnInfo(name = "precipIn")
    private double precipitationVolume;
    @ColumnInfo(name = "feelslikeF")
    private double feelsLikeTemperature;
    @ColumnInfo(name = "visMiles")
    private double visibilityDistance;

    private ImperialWeather(double temperature, String conditionText, String conditionIconUrl, double windSpeed,
                            double precipitationVolume, double feelsLikeTemperature, double visibilityDistance) {
        this.temperature = temperature;
        this.conditionText = conditionText;
        this.conditionIconUrl = conditionIconUrl;
        this.windSpeed = windSpeed;
        this.precipitationVolume = precipitationVolume;
        this.feelsLikeTemperature = feelsLikeTemperature;
        this.visibilityDistance = visibilityDistance;
    }

    @Override
    public double getTemperature() {
        return temperature;
    }

    private void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    @Override
    public String getConditionText() {
        return conditionText;
    }

    private void setConditionText(String conditionText) {
        this.conditionText = conditionText;
    }

    @Override
    public String getConditionIconUrl() {
        return conditionIconUrl;
    }

    private void setConditionIconUrl(String conditionIconUrl) {
        this.conditionIconUrl = conditionIconUrl;
    }

    @Override
    public double getWindSpeed() {
        return windSpeed;
    }

    private void setWindSpeed(double windSpeed) {
        this.windSpeed = windSpeed;
    }

    @Override
    public double getPrecipitationVolume() {
        return precipitationVolume;
    }

    private void setPrecipitationVolume(double precipitationVolume) {
        this.precipitationVolume = precipitationVolume;
    }

    @Override
    public double getFeelsLikeTemperature() {
        return feelsLikeTemperature;
    }

    private void setFeelsLikeTemperature(double feelsLikeTemperature) {
        this.feelsLikeTemperature = feelsLikeTemperature;
    }

    @Override
    public double getVisibilityDistance() {
        return visibilityDistance;
    }

    private void setVisibilityDistance(double visibilityDistance) {
        this.visibilityDistance = visibilityDistance;
    }
}