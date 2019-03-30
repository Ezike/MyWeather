package ezike.tobenna.myweather.di.module;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import ezike.tobenna.myweather.ui.WeatherFragment;

/**
 * @author tobennaezike
 */

@Module
public abstract class FragmentBuildersModule {

    @ContributesAndroidInjector
    abstract WeatherFragment contributeWeatherFragmet();
}
