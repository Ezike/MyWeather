package ezike.tobenna.myweather.di.module;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import ezike.tobenna.myweather.data.remote.WeatherFetchService;

/**
 * @author tobennaezike
 */
@Module
public abstract class ServiceBuilderModule {

    @ContributesAndroidInjector
    abstract WeatherFetchService contributeWeatherFetchService();

}