package ezike.tobenna.myweather.di.module;

import dagger.Binds;
import dagger.Module;
import ezike.tobenna.myweather.data.local.LocalDataSource;
import ezike.tobenna.myweather.data.local.LocalDataSourceImpl;
import ezike.tobenna.myweather.data.remote.RemoteImpl;
import ezike.tobenna.myweather.data.remote.RemoteSource;
import ezike.tobenna.myweather.repository.Repository;
import ezike.tobenna.myweather.repository.WeatherRepository;

@Module
public abstract class DataSourceModule {

    @Binds
    abstract LocalDataSource provideDataSource(LocalDataSourceImpl localDataSource);

    @Binds
    abstract Repository provideRepoImpl(WeatherRepository repo);

    @Binds
    abstract RemoteSource provideRemoteImpl(RemoteImpl remote);
}

