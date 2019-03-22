package ezike.tobenna.myweather.data.local;


import androidx.room.Database;
import androidx.room.RoomDatabase;
import ezike.tobenna.myweather.data.local.entity.CurrentWeather;

/**
 * @author tobennaezike
 */

@Database(entities = {CurrentWeather.class}, version = 1, exportSchema = false)
public abstract class WeatherDatabase extends RoomDatabase {

    public abstract CurrentWeatherDao currentWeatherDao();
}
