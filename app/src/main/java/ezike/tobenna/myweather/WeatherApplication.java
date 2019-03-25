package ezike.tobenna.myweather;

import android.app.Activity;
import android.app.Application;

import com.jakewharton.threetenabp.AndroidThreeTen;

import javax.inject.Inject;

import androidx.preference.PreferenceManager;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;
import ezike.tobenna.myweather.di.AppInjector;
import timber.log.Timber;

/**
 * @author tobennaezike
 */
public class WeatherApplication extends Application implements HasActivityInjector {

    @Inject
    DispatchingAndroidInjector<Activity> dispatchingAndroidInjector;

    @Override
    public void onCreate() {

        AppInjector.init(this);

        super.onCreate();
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }

        AndroidThreeTen.init(this);
        PreferenceManager.setDefaultValues(this, R.xml.preferences, false);
    }

    @Override
    public AndroidInjector<Activity> activityInjector() {
        return dispatchingAndroidInjector;
    }
}
