package ezike.tobenna.myweather.di.module;

import android.app.Application;

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
class DatabaseModule {

    @Provides
    @Singleton
    WeatherDatabase provideDatabase(@NonNull Application application) {
        return Room.databaseBuilder(application,
                WeatherDatabase.class, "weather_db")
                .build();
    }

    @Provides
    @Singleton
    CurrentWeatherDao provideCurrentWeatherDao(@NonNull WeatherDatabase appDatabase) {
        return appDatabase.currentWeatherDao();
    }

}
