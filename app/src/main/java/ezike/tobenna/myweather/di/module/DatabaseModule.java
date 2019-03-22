package ezike.tobenna.myweather.di.module;

import android.content.Context;

import javax.inject.Singleton;

import androidx.annotation.NonNull;
import androidx.room.Room;
import dagger.Module;
import dagger.Provides;
import ezike.tobenna.myweather.data.local.CurrentWeatherDao;
import ezike.tobenna.myweather.data.local.WeatherDatabase;

/**
 * @author tobennaezike
 */
@Module
public class DatabaseModule {

    @Provides
    @Singleton
    static WeatherDatabase provideDatabase(@NonNull Context context) {
        return Room.databaseBuilder(context,
                WeatherDatabase.class, "weather_db")
                .build();
    }

    @Provides
    @Singleton
    static CurrentWeatherDao provideCurrentWeatherDao(@NonNull WeatherDatabase appDatabase) {
        return appDatabase.currentWeatherDao();
    }

}
