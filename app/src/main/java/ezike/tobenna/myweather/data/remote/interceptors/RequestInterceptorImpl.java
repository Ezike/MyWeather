package ezike.tobenna.myweather.data.remote.interceptors;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import javax.inject.Inject;
import javax.inject.Singleton;

import ezike.tobenna.myweather.BuildConfig;
import okhttp3.HttpUrl;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @author tobennaezike
 */

@Singleton
public class RequestInterceptorImpl implements ApiInterceptor {

    @Inject
    public RequestInterceptorImpl() {
    }

    @NotNull
    @Override
    public Response intercept(@NotNull Chain chain) throws IOException {
        Request request = chain.request();

        HttpUrl url = request.url().newBuilder()
                .addQueryParameter("key", BuildConfig.ApiKey)
                .build();

        request = request.newBuilder().url(url).build();
        return chain.proceed(request);
    }
}
