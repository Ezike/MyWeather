package ezike.tobenna.myweather.di;

import android.app.Application;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.support.AndroidSupportInjectionModule;
import ezike.tobenna.myweather.WeatherApplication;
import ezike.tobenna.myweather.di.module.ActivityModule;
import ezike.tobenna.myweather.di.module.ApiModule;
import ezike.tobenna.myweather.di.module.AppModule;
import ezike.tobenna.myweather.di.module.DataSourceModule;
import ezike.tobenna.myweather.di.module.DatabaseModule;
import ezike.tobenna.myweather.di.module.FragmentBuildersModule;
import ezike.tobenna.myweather.di.module.LocationModule;
import ezike.tobenna.myweather.di.module.UnitModule;


/**
 * @author tobennaezike
 * @since 20/03/19
 */
@Singleton
@Component(modules = {
        AndroidSupportInjectionModule.class,
        ActivityModule.class,
        FragmentBuildersModule.class,
        ApiModule.class,
        DatabaseModule.class,
        AppModule.class,
        UnitModule.class,
        LocationModule.class,
        DataSourceModule.class,
        ViewModelModule.class})
public interface AppComponent {

    void inject(WeatherApplication application);

    @Component.Builder
    interface Builder {

        @BindsInstance
        Builder application(Application application);

        AppComponent build();
    }
}
