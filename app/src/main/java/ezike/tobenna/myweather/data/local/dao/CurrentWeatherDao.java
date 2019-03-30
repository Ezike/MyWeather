package ezike.tobenna.myweather.data.local.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import ezike.tobenna.myweather.data.local.entity.CurrentWeather;
import ezike.tobenna.myweather.data.local.units.ImperialWeather;
import ezike.tobenna.myweather.data.local.units.MetricWeather;

/**
 * @author tobennaezike
 */
@Dao
public interface CurrentWeatherDao {

    @Query("select * from current_weather")
    LiveData<CurrentWeather> getCurrentWeather();

    @Query("select * from current_weather")
    LiveData<ImperialWeather> getImperialWeather();

    @Query("select * from current_weather")
    LiveData<MetricWeather> getMetricWeather();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertCurrentWeather(CurrentWeather weather);
}
