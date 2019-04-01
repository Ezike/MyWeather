package ezike.tobenna.myweather.widget;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import timber.log.Timber;

public class WidgetUpdateService extends Service {

    public WidgetUpdateService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        WeatherWidgetProvider.updateWidget(this);
        Timber.d("widget update service started");
        return super.onStartCommand(intent, flags, startId);
    }
}
