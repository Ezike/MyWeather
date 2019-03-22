package ezike.tobenna.myweather.di.module;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    @Provides
    @Singleton
    static Context provideContext(Application application) {
        return application.getApplicationContext();
    }

}
