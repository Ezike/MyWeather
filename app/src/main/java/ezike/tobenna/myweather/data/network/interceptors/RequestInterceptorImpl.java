package ezike.tobenna.myweather.data.network.interceptors;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import ezike.tobenna.myweather.BuildConfig;
import okhttp3.HttpUrl;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @author tobennaezike
 */

public class RequestInterceptorImpl implements ApiInterceptor {

    public RequestInterceptorImpl() {
    }

    @NotNull
    @Override
    public Response intercept(@NotNull Chain chain) throws IOException {
        Request originalRequest = chain.request();
        HttpUrl originalUrl = originalRequest.url();
        HttpUrl url = originalUrl.newBuilder()
                .addQueryParameter("api_key", BuildConfig.ApiKey)
                .build();

        Request.Builder requestBuilder = originalRequest.newBuilder().url(url);
        Request request = requestBuilder.build();
        return chain.proceed(request);
    }
}
