package ezike.tobenna.myweather.data.local.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import ezike.tobenna.myweather.data.local.entity.WeatherResponse;

/**
 * @author tobennaezike
 */

@Dao
public interface WeatherDao {

    @Query("select * from weather_response")
    LiveData<WeatherResponse> getWeatherResponse();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertWeatherResponse(WeatherResponse weather);
}
