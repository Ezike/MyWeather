package ezike.tobenna.myweather.di.module;

import dagger.Binds;
import dagger.Module;
import ezike.tobenna.myweather.data.local.LocalDataSource;
import ezike.tobenna.myweather.data.local.LocalDataSourceImpl;
import ezike.tobenna.myweather.data.remote.RemoteSourceImpl;
import ezike.tobenna.myweather.data.source.BaseSource;
import ezike.tobenna.myweather.repository.Repo;
import ezike.tobenna.myweather.repository.RepoImpl;

@Module
public abstract class DataSourceModule {

    @Binds
    abstract LocalDataSource provideDataSource(LocalDataSourceImpl localDataSource);

    @Binds
    abstract BaseSource provideBaseSource(RemoteSourceImpl remoteSource);

    @Binds
    abstract Repo provideRepoImpl(RepoImpl repo);
}

