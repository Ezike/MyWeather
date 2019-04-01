package ezike.tobenna.myweather.data.local;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import ezike.tobenna.myweather.data.local.dao.WeatherDao;
import ezike.tobenna.myweather.data.local.entity.WeatherResponse;

/**
 * @author tobennaezike
 */

@Database(entities = {WeatherResponse.class}, version = 1, exportSchema = false)
@TypeConverters({WeatherTypeConverter.class})
public abstract class WeatherDatabase extends RoomDatabase {

    public abstract WeatherDao weatherDao();
}
