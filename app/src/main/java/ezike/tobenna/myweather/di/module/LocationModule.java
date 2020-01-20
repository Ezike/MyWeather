package ezike.tobenna.myweather.di.module;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import ezike.tobenna.myweather.provider.LocationProvider;
import ezike.tobenna.myweather.provider.LocationProviderImpl;

/**
 * @author tobennaezike
 */

@Module
public abstract class LocationModule {

    @Singleton
    @Binds
    abstract LocationProvider provideLocationProvider(LocationProviderImpl locationProvider);

}
