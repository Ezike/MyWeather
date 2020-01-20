package ezike.tobenna.myweather.di.module

import android.app.Application
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import ezike.tobenna.myweather.AppCoroutineDispatchers
import ezike.tobenna.myweather.BuildConfig
import ezike.tobenna.myweather.data.remote.api.ApiService
import ezike.tobenna.myweather.data.remote.interceptors.ConnectivityInterceptor
import ezike.tobenna.myweather.data.remote.interceptors.RequestInterceptor
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.OkHttpClient.Builder
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.io.File
import javax.inject.Singleton

/**
 * @author tobennaezike
 */

@Module
object ApiModule {
    @Provides
    @JvmStatic
    @Singleton
    internal fun provideCache(application: Application): Cache {
        val cacheSize = 10 * 1024 * 1024.toLong() // 10 MB
        val httpCacheDirectory = File(application.cacheDir, "http-cache")
        return Cache(httpCacheDirectory, cacheSize)
    }

    @Provides
    @Singleton
    internal fun provideOkHttpClient(cache: Cache, connectivityInterceptor: ConnectivityInterceptor,
                                     requestInterceptor: RequestInterceptor,
                                     logger: HttpLoggingInterceptor): OkHttpClient {
        val httpClient = Builder()
        httpClient.cache(cache)
        httpClient.addInterceptor(logger)
        httpClient.addNetworkInterceptor(requestInterceptor)
        httpClient.addInterceptor(connectivityInterceptor)
        return httpClient.build()
    }

    @Provides
    @Singleton
    internal fun provideOkHttpLogger(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            if (BuildConfig.DEBUG) {
                level = Level.BODY
            }
        }
    }

    @Provides
    @Singleton
    internal fun provideMoshi(): Moshi {
        return Moshi.Builder()
                .add(KotlinJsonAdapterFactory()).build()
    }

    @Singleton
    @Provides
    internal fun provideApiService(moshi: Moshi, okHttpClient: OkHttpClient): ApiService {
        return Retrofit
                .Builder()
                .baseUrl(BuildConfig.BaseUrl)
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .client(okHttpClient)
                .build()
                .create(ApiService::class.java)
    }

    @Provides
    fun provideDispatcher(): AppCoroutineDispatchers {
        return AppCoroutineDispatchers()
    }
}