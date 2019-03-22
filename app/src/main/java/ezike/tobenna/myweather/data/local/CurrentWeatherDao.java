package ezike.tobenna.myweather.data.local;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import ezike.tobenna.myweather.data.local.entity.CurrentWeather;

/**
 * @author tobennaezike
 */
@Dao
public interface CurrentWeatherDao {

    @Query("select * from current_weather")
    LiveData<CurrentWeather> getImperialWeather();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insertCurrentWeather(CurrentWeather weather);
}
