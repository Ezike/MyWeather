package ezike.tobenna.myweather.di.module;

import android.app.Application;
import android.content.Context;
import android.location.LocationManager;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

import static android.content.Context.LOCATION_SERVICE;

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

    @Singleton
    @Provides
    static FusedLocationProviderClient provideFusedLocationProviderClient(Context context) {
        return LocationServices.getFusedLocationProviderClient(context);
    }

    @Singleton
    @Provides
    static LocationManager provideLocatioManager(Context context) {
        return (LocationManager) context.getSystemService(LOCATION_SERVICE);
    }
}
