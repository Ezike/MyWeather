package ezike.tobenna.myweather.data.network.interceptors;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import javax.inject.Inject;
import javax.inject.Singleton;

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
        if (!isOnline()) {
            throw new IOException();
        }
        return chain.proceed(chain.request());
    }

    private Boolean isOnline() {
        ConnectivityManager connectivityManager = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }
}
