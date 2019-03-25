package ezike.tobenna.myweather.data.local.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import ezike.tobenna.myweather.data.local.entity.WeatherLocation;

@Dao
public interface WeatherLocationDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insert(WeatherLocation weatherLocation);

    @Query("select * from weather_location")
    LiveData<WeatherLocation> getLocation();

    @Query("select * from weather_location")
    WeatherLocation getCustomLocation();
}
