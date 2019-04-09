package ezike.tobenna.myweather.di.module;

import androidx.lifecycle.LiveData;
import dagger.Binds;
import dagger.Module;
import ezike.tobenna.myweather.data.local.LocalDataSource;
import ezike.tobenna.myweather.data.local.LocalDataSourceImpl;
import ezike.tobenna.myweather.data.local.entity.WeatherResponse;
import ezike.tobenna.myweather.data.remote.RemoteSourceImpl;
import ezike.tobenna.myweather.data.source.BaseSource;
import ezike.tobenna.myweather.repository.WeatherRepository;
import ezike.tobenna.myweather.repository.WeatherRepositoryImpl;
import ezike.tobenna.myweather.utils.Resource;

@Module
public abstract class DataSourceModule {

    @Binds
    abstract LocalDataSource provideDataSource(LocalDataSourceImpl localDataSource);

    @Binds
    abstract BaseSource provideBaseSource(RemoteSourceImpl remoteSource);

    @Binds
    abstract WeatherRepository<LiveData<Resource<WeatherResponse>>> provideRepoImpl(WeatherRepositoryImpl repo);
}

