package ezike.tobenna.myweather.di.module;

import javax.inject.Named;

import dagger.Binds;
import dagger.Module;
import ezike.tobenna.myweather.data.remote.interceptors.ApiInterceptor;
import ezike.tobenna.myweather.data.remote.interceptors.ConnectivityInterceptorImpl;
import ezike.tobenna.myweather.data.remote.interceptors.RequestInterceptorImpl;

/**
 * @author tobennaezike
 * @since 20/03/19
 */
@Module(includes = AppModule.class)
public abstract class ClientModule {

    @Binds
    @Named("connect")
    abstract ApiInterceptor provideConnectivityInterceptor(ConnectivityInterceptorImpl interceptor);

    @Binds
    @Named("request")
    abstract ApiInterceptor provideRequestInterceptor(RequestInterceptorImpl interceptor);
}
