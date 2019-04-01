package ezike.tobenna.myweather.data.local;

import com.squareup.moshi.Moshi;

import java.io.IOException;

import androidx.room.TypeConverter;
import ezike.tobenna.myweather.data.model.CurrentWeather;
import ezike.tobenna.myweather.data.model.Forecast;
import ezike.tobenna.myweather.data.model.WeatherLocation;

/**
 * @author tobennaezike
 */
public class WeatherTypeConverter {

    private Moshi moshi = new Moshi.Builder().build();

    @TypeConverter
    public String weatherToJson(CurrentWeather weather) {
        return weather == null ? null : moshi.adapter(CurrentWeather.class).toJson(weather);
    }

    @TypeConverter
    public CurrentWeather weatherFromJson(String string) {
        CurrentWeather weather;
        try {
            weather = moshi.adapter(CurrentWeather.class).fromJson(string);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return weather;
    }


    @TypeConverter
    public String locationToJson(WeatherLocation weatherLocation) {
        return weatherLocation == null ? null : moshi.adapter(WeatherLocation.class).toJson(weatherLocation);
    }

    @TypeConverter
    public WeatherLocation locationFromJson(String string) {
        WeatherLocation location;
        try {
            location = moshi.adapter(WeatherLocation.class).fromJson(string);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return location;
    }


    @TypeConverter
    public String forecastToJson(Forecast forecast) {
        return forecast == null ? null : moshi.adapter(Forecast.class).toJson(forecast);
    }

    @TypeConverter
    public Forecast forecastFromJson(String string) {
        Forecast weatherForecast;
        try {
            weatherForecast = moshi.adapter(Forecast.class).fromJson(string);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return weatherForecast;
    }
}
