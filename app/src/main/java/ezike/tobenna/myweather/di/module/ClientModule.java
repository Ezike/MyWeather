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
@Module(includes = AppModule.class)
abstract class ClientModule {

    @Binds
    @Named("connect")
    abstract ApiInterceptor provideConnectivityInterceptor(ConnectivityInterceptorImpl interceptor);

    @Binds
    @Named("request")
    abstract ApiInterceptor provideRequestInterceptor(RequestInterceptorImpl interceptor);
}
