package ezike.tobenna.myweather.utils;

import android.content.Context;

import com.github.pwittchen.weathericonview.WeatherIconView;

import ezike.tobenna.myweather.R;

public class WeatherIconUtils {

    public static void getIconResource(Context context, WeatherIconView iconView, String condition) {
        if (condition != null) {
            if (condition.contains("rain")) {
                iconView.setIconResource(context.getString(R.string.wi_rain));
            } else if (condition.contains("snow")) {
                iconView.setIconResource(context.getString(R.string.wi_snow));
            } else if (condition.contains("sun")) {
                iconView.setIconResource(context.getString(R.string.wi_day_sunny));
            } else if (condition.contains("cloud")) {
                iconView.setIconResource(context.getString(R.string.wi_forecast_io_cloudy));
            } else if (condition.contains("Clear")) {
                iconView.setIconResource(context.getString(R.string.wi_wu_clear));
            } else if (condition.contains("Overcast")) {
                iconView.setIconResource(context.getString(R.string.wi_day_sunny_overcast));
            } else if (condition.contains("sleet")) {
                iconView.setIconResource(context.getString(R.string.wi_day_sleet_storm));
            } else if (condition.contains("Mist")) {
                iconView.setIconResource(context.getString(R.string.wi_fog));
            } else if (condition.contains("drizzle")) {
                iconView.setIconResource(context.getString(R.string.wi_raindrops));
            } else if (condition.contains("thunderstorm")) {
                iconView.setIconResource(context.getString(R.string.wi_wu_tstorms));
            } else if (condition.contains("Thunder")) {
                iconView.setIconResource(context.getString(R.string.wi_thunderstorm));
            } else if (condition.contains("Cloudy")) {
                iconView.setIconResource(context.getString(R.string.wi_forecast_io_cloudy));
            } else if (condition.contains("Fog")) {
                iconView.setIconResource(context.getString(R.string.wi_forecast_io_fog));
            } else if (condition.contains("Sunny")) {
                iconView.setIconResource(context.getString(R.string.wi_wu_mostlysunny));
            } else if (condition.contains("Blizzard")) {
                iconView.setIconResource(context.getString(R.string.wi_snow_wind));
            } else if (condition.contains("Ice")) {
                iconView.setIconResource(context.getString(R.string.wi_wu_chancesnow));
            } else if (condition.contains("ice")) {
                iconView.setIconResource(context.getString(R.string.wi_forecast_io_snow));
            } else if (condition.contains("Rain")) {
                iconView.setIconResource(context.getString(R.string.wi_rain_wind));
            } else if (condition.contains("wind")) {
                iconView.setIconResource(context.getString(R.string.wi_windy));
            } else if (condition.contains("Wind")) {
                iconView.setIconResource(context.getString(R.string.wi_strong_wind));
            } else if (condition.contains("storm")) {
                iconView.setIconResource(context.getString(R.string.wi_storm_warning));
            } else if (condition.contains("Storm")) {
                iconView.setIconResource(context.getString(R.string.wi_forecast_io_thunderstorm));
            } else if (condition.contains("thunder")) {
                iconView.setIconResource(context.getString(R.string.wi_day_snow_thunderstorm));
            } else {
                iconView.setIconResource(context.getString(R.string.wi_forecast_io_partly_cloudy_day));
            }
        }
    }

}
