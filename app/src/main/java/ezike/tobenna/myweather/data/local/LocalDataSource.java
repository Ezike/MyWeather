package ezike.tobenna.myweather.data.local;

import ezike.tobenna.myweather.data.source.BaseSource;

public interface LocalDataSource<K, V> extends BaseSource {

    void save(K k);

    boolean hasLocationChanged(K k);

    boolean shouldFetch(K k);

    @Override
    V get();
}
