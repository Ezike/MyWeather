package ezike.tobenna.myweather.data.remote.interceptors;

import android.content.Context;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import javax.inject.Inject;
import javax.inject.Singleton;

import ezike.tobenna.myweather.utils.Utilities;
import okhttp3.Response;

/**
 * @author tobennaezike
 */

@Singleton
public class ConnectivityInterceptorImpl implements ApiInterceptor {

    private Context mContext;

    @Inject
    public ConnectivityInterceptorImpl(Context context) {
        mContext = context.getApplicationContext();
    }

    @NotNull
    @Override
    public Response intercept(@NotNull Chain chain) throws IOException {
        if (!Utilities.isOnline(mContext)) {
            throw new IOException();
        }
        return chain.proceed(chain.request());
    }
}
