package ezike.tobenna.myweather.di.module

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.location.LocationManager
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * @author tobennaezike
 * @since 22/03/19
 */
@Module
object AppModule {
    @Provides
    @Singleton
    internal fun provideContext(application: Application): Context {
        return application.applicationContext
    }

    @Singleton
    @Provides
    internal fun provideFusedLocationProviderClient(context: Context): FusedLocationProviderClient {
        return LocationServices.getFusedLocationProviderClient(context)
    }

    @Singleton
    @Provides
    internal fun provideLocationManager(context: Context): LocationManager {
        return context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
    }

    @Singleton
    @Provides
    internal fun providePrefs(context: Context): SharedPreferences {
        return context.getSharedPreferences(WIDGET_PREF, Context.MODE_PRIVATE)
    }

    private const val WIDGET_PREF = "ezike.tobenna.myweather.ui.widget.pref"
}