package ezike.tobenna.myweather.di.module;

import android.content.Context;

import javax.inject.Singleton;

import androidx.annotation.NonNull;
import androidx.room.Room;
import dagger.Module;
import dagger.Provides;
import ezike.tobenna.myweather.data.local.db.WeatherDao;
import ezike.tobenna.myweather.data.local.db.WeatherDatabase;


/**
 * @author tobennaezike
 * @since 20/03/19
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
    static WeatherDao provideWeatherResponseDao(@NonNull WeatherDatabase appDatabase) {
        return appDatabase.weatherDao();
    }
}
