package ezike.tobenna.myweather.widget;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.SystemClock;
import android.widget.RemoteViews;

import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.AppWidgetTarget;
import com.bumptech.glide.request.transition.Transition;

import androidx.annotation.NonNull;
import ezike.tobenna.myweather.R;
import ezike.tobenna.myweather.ui.activity.MainActivity;
import ezike.tobenna.myweather.ui.fragment.WeatherFragment;
import ezike.tobenna.myweather.utils.GlideApp;

/**
 * Implementation of App Widget functionality.
 */
public class WeatherWidgetProvider extends AppWidgetProvider {

    private PendingIntent pendingIntent;

    private AlarmManager manager;

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

        SharedPreferences sharedPreferences = context.getSharedPreferences(WeatherFragment.WIDGET_PREF, Context.MODE_PRIVATE);
        String defaultValue = context.getString(R.string.no_data);
        String conditionText = sharedPreferences.getString(WeatherFragment.WIDGET_TEXT, defaultValue);
        String location = sharedPreferences.getString(WeatherFragment.WIDGET_LOCATION, defaultValue);
        String iconUrl = sharedPreferences.getString(WeatherFragment.WIDGET_ICON, defaultValue);

        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.weather_widget);
        views.setTextViewText(R.id.appwidget_location, location);
        views.setTextViewText(R.id.appwidget_condition, context.getString(R.string.widget_forecast, conditionText));

        Intent clickIntent = new Intent(context, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, clickIntent, 0);
        views.setOnClickPendingIntent(R.id.appwidget_root, pendingIntent);

        // Display weather condition icon using Glide
        showWeatherIcon(context, appWidgetId, iconUrl, views);

        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    public static void updateWidget(Context context) {
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
        int[] appWidgetIds = appWidgetManager.getAppWidgetIds(new ComponentName(context, WeatherWidgetProvider.class));
        for (int appWidgetId : appWidgetIds) {
            WeatherWidgetProvider.updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    private static void showWeatherIcon(Context context, int appWidgetId, String iconUrl, RemoteViews views) {
        AppWidgetTarget widgetTarget = new AppWidgetTarget(context, R.id.appwidget_icon, views, appWidgetId) {
            @Override
            public void onResourceReady(@NonNull Bitmap resource, Transition<? super Bitmap> transition) {
                super.onResourceReady(resource, transition);
            }
        };

        RequestOptions options = new RequestOptions().
                override(300, 300).placeholder(R.drawable.day).error(R.drawable.day);

        GlideApp.with(context.getApplicationContext())
                .asBitmap()
                .load("http:" + iconUrl)
                .apply(options)
                .into(widgetTarget);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
            startWidgetUpdateService(context);
        }
        super.onUpdate(context, appWidgetManager, appWidgetIds);
    }

    private void startWidgetUpdateService(Context context) {
        manager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        final Intent updateIntent = new Intent(context, WidgetUpdateService.class);

        if (pendingIntent == null) {
            pendingIntent = PendingIntent.getService(context, 0, updateIntent, PendingIntent.FLAG_CANCEL_CURRENT);
        }
        manager.setRepeating(AlarmManager.ELAPSED_REALTIME, SystemClock.elapsedRealtime(), 60000, pendingIntent);
    }

    @Override
    public void onEnabled(Context context) {
    }

    @Override
    public void onDisabled(Context context) {
        if (manager != null) {
            manager.cancel(pendingIntent);
        }
    }
}

