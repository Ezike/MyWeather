package ezike.tobenna.myweather.data.network.interceptors;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Response;

/**
 * @author tobennaezike
 */
public class ConnectivityInterceptorImpl implements ConnectivityInterceptor {

    private Context mContext;

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
