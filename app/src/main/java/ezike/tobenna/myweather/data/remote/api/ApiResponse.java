package ezike.tobenna.myweather.data.remote.api;

import java.io.IOException;

import androidx.annotation.Nullable;
import retrofit2.Response;
import timber.log.Timber;

/**
 * @author tobennaezike
 */
public class ApiResponse<T> {

    private final int code;
    @Nullable
    public final T body;
    @Nullable
    private final Throwable error;


    public ApiResponse(@Nullable Throwable error) {
        code = 500;
        body = null;
        this.error = error;
    }

    public ApiResponse(Response<T> response) {
        code = response.code();
        if (response.isSuccessful()) {
            body = response.body();
            error = null;
        } else {
            String message = null;
            if (response.errorBody() != null) {
                try {
                    message = response.errorBody().string();
                } catch (IOException ignored) {
                    Timber.e(ignored, "error while parsing response");
                }
            }
            if (message == null || message.trim().length() == 0) {
                message = response.message();
            }
            error = new IOException(message);
            body = null;
        }
    }

    public boolean isSuccessful() {
        return code >= 200 && code < 300;
    }


    public int getCode() {
        return code;
    }

    @Nullable
    public T getBody() {
        return body;
    }

    @Nullable
    public Throwable getError() {
        return error;
    }
}
