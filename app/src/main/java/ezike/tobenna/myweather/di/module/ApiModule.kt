package ezike.tobenna.myweather.di.module

import android.app.Application
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import ezike.tobenna.myweather.data.remote.api.ApiService
import ezike.tobenna.myweather.data.remote.interceptors.ConnectivityInterceptor
import ezike.tobenna.myweather.data.remote.interceptors.RequestInterceptor
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
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
                                     requestInterceptor: RequestInterceptor): OkHttpClient {
        val logging = HttpLoggingInterceptor()
        logging.level = Level.BODY
        val httpClient = Builder()
        httpClient.cache(cache)
        httpClient.addInterceptor(logging)
        httpClient.addNetworkInterceptor(requestInterceptor)
        httpClient.addInterceptor(connectivityInterceptor)
        return httpClient.build()
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
                .baseUrl("http://api.weatherstack.com/")
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .client(okHttpClient)
                .build()
                .create(ApiService::class.java)
    }

    @Provides
    fun provideDispatcher(): CoroutineDispatcher {
        return Dispatchers.IO
    }
}