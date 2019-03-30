package ezike.tobenna.myweather.data.remote.api;

import androidx.lifecycle.LiveData;
import ezike.tobenna.myweather.data.local.entity.WeatherResponse;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * @author tobennaezike
 */
// https://api.apixu.com/v1/forecast.json?key=ENTER_KEY&q=Los%20Angeles&days=1
public interface ApiService {

    @GET("forecast.json")
    LiveData<ApiResponse<WeatherResponse>> getWeather(@Query("q") String location,
                                                      @Query("days") Integer days,
                                                      @Query("lang") String languageCode);

}

