package ezike.tobenna.myweather.di.module;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import ezike.tobenna.myweather.ui.activity.MainActivity;
import ezike.tobenna.myweather.ui.fragment.WeatherFragment;

/**
 * @author tobennaezike
 */

@Module
public abstract class ActivityModule {

    @ContributesAndroidInjector
    abstract MainActivity contributeMainActivity();

    @ContributesAndroidInjector
    abstract WeatherFragment contributeWeatherFragment();
}
