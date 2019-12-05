package ezike.tobenna.myweather;

import androidx.multidex.MultiDexApplication;
import androidx.preference.PreferenceManager;

import com.jakewharton.threetenabp.AndroidThreeTen;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasAndroidInjector;
import ezike.tobenna.myweather.di.DaggerAppComponent;
import timber.log.Timber;

/**
 * @author tobennaezike
 */
public class WeatherApplication extends MultiDexApplication implements HasAndroidInjector {

    @Inject
    DispatchingAndroidInjector<Object> dispatchingAndroidInjector;

    @Override
    public void onCreate() {
        DaggerAppComponent
                .builder()
                .application(this)
                .build()
                .inject(this);

        super.onCreate();
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }

        AndroidThreeTen.init(this);

        PreferenceManager.setDefaultValues(this, R.xml.preferences, false);

    }

    @Override
    public AndroidInjector<Object> androidInjector() {
        return dispatchingAndroidInjector;
    }
}
