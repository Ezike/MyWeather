package ezike.tobenna.myweather.di

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import ezike.tobenna.myweather.WeatherApplication
import ezike.tobenna.myweather.di.module.*
import javax.inject.Singleton

/**
 * @author tobennaezike
 * @since 20/03/19
 */
@Singleton
@Component(modules = [AndroidSupportInjectionModule::class,
    ActivityModule::class,
    ApiModule::class, DatabaseModule::class,
    AppModule::class, UnitModule::class,
    LocationModule::class,
    DataSourceModule::class,
    ViewModelModule::class])

interface AppComponent {
    fun inject(application: WeatherApplication?)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}