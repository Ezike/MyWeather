package ezike.tobenna.myweather.data.network.api;

import androidx.lifecycle.LiveData;
import ezike.tobenna.myweather.data.WeatherResponse;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * @author tobennaezike
 */
// https://api.apixu.com/v1/forecast.json?key=89e8bd89085b41b7a4b142029180210&q=Los%20Angeles&days=1
public interface ApiService {

    @GET("forecast.json")
    LiveData<ApiResponse<WeatherResponse>> getWeather(@Query("q") String location,
                                                      @Query("days") Integer days,
                                                      @Query("lang") String languageCode);
}

