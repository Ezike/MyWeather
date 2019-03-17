package ezike.tobenna.myweather;

import android.app.Application;

import com.jakewharton.threetenabp.AndroidThreeTen;

import timber.log.Timber;

/**
 * @author tobennaezike
 */
public class WeatherApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }

        AndroidThreeTen.init(this);
    }
}
