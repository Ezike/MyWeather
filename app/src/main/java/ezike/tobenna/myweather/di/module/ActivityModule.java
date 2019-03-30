package ezike.tobenna.myweather.di.module;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import ezike.tobenna.myweather.ui.MainActivity;

/**
 * @author tobennaezike
 */

@Module
public abstract class ActivityModule {

    @ContributesAndroidInjector(modules = FragmentBuildersModule.class)
    abstract MainActivity contributeMainActivity();
}
