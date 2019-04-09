package ezike.tobenna.myweather.repository;

import androidx.lifecycle.LiveData;

public interface WeatherRepository<V> {
    LiveData<V> loadData(String s);
}
