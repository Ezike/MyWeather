package ezike.tobenna.myweather.data.local;


import androidx.room.Database;
import androidx.room.RoomDatabase;
import ezike.tobenna.myweather.data.local.dao.CurrentWeatherDao;
import ezike.tobenna.myweather.data.local.dao.WeatherLocationDao;
import ezike.tobenna.myweather.data.local.entity.CurrentWeather;
import ezike.tobenna.myweather.data.local.entity.WeatherLocation;

/**
 * @author tobennaezike
 */

@Database(entities = {CurrentWeather.class, WeatherLocation.class}, version = 1, exportSchema = false)
public abstract class WeatherDatabase extends RoomDatabase {

    public abstract CurrentWeatherDao currentWeatherDao();

    public abstract WeatherLocationDao weatherLocationDao();
}
