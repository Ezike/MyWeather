package ezike.tobenna.myweather.repository;

import androidx.lifecycle.LiveData;

public interface Repo<V> {
    LiveData<V> loadData(String s);
}
