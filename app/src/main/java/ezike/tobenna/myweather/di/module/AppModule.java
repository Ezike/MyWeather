package ezike.tobenna.myweather.di.module;

import android.app.Application;
import android.content.Context;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * @author tobennaezike
 * @since 22/03/19
 */
@Module
public class AppModule {

    @Provides
    @Singleton
    static Context provideContext(Application application) {
        return application.getApplicationContext();
    }

    static FusedLocationProviderClient provideFusedLocationProviderClient(Context context) {
        return LocationServices.getFusedLocationProviderClient(context);
    }
}
