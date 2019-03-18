package ezike.tobenna.myweather.di.module;

import javax.inject.Named;

import dagger.Binds;
import dagger.Module;
import ezike.tobenna.myweather.data.network.interceptors.ApiInterceptor;
import ezike.tobenna.myweather.data.network.interceptors.ConnectivityInterceptorImpl;
import ezike.tobenna.myweather.data.network.interceptors.RequestInterceptorImpl;

/**
 * @author tobennaezike
 */
@Module
abstract class ClientModule {

    @Binds
    @Named("connectivityInterceptor")
    abstract ApiInterceptor provideConnectivityInterceptor(ConnectivityInterceptorImpl interceptor);

    @Binds
    @Named("RequestInterceptor")
    abstract ApiInterceptor provideRequestInterceptor(RequestInterceptorImpl interceptor);
}
