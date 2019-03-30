package ezike.tobenna.myweather.data.remote;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 *
 * @author tobennaezike
 */
public class WeatherFetchService extends IntentService {

    private static final String ACTION_FETCH_WEATHER = "ezike.tobenna.myweather.data.network.action.FOO";

    @Inject
    RemoteDataSource mRemoteDataSource;

    public WeatherFetchService() {
        super("WeatherFetchService");
    }

    public static void startActionFetchWeather(Context context) {
        Intent intent = new Intent(context, WeatherFetchService.class);
        intent.setAction(ACTION_FETCH_WEATHER);
        context.startService(intent);
    }

    @Override
    public void onCreate() {
        AndroidInjection.inject(this);
        super.onCreate();
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_FETCH_WEATHER.equals(action)) {
                handleAction();
            }
        }
    }

    private void handleAction() {
        mRemoteDataSource.fetchWeather();
    }

}
