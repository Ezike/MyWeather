package ezike.tobenna.myweather.di.module;

import dagger.Binds;
import dagger.Module;
import ezike.tobenna.myweather.provider.LocationProvider;
import ezike.tobenna.myweather.provider.LocationProviderImpl;

/**
 * @author tobennaezike
 */

@Module
public abstract class LocationModule {

    @Binds
    abstract LocationProvider provideLocationProvider(LocationProviderImpl locationProvider);

}
