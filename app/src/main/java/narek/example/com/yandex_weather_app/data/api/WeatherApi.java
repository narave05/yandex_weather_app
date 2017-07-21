package narek.example.com.yandex_weather_app.data.api;

import io.reactivex.Single;
import narek.example.com.yandex_weather_app.model.rest.WeatherDataRes;
import narek.example.com.yandex_weather_app.util.AppConfig;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class WeatherApi {

    WeatherRestService weatherRestService = RestServiceGenerator.createService(WeatherRestService.class,
            AppConfig.BASE_URL);

    public Single<WeatherDataRes> callWeatherDataByCityName(String cityName) {
        return weatherRestService.callWeatherDataByCityName(AppConfig.API_ID, cityName);
    }

    private interface WeatherRestService {
        @GET("weather")
        Single<WeatherDataRes> callWeatherDataByCityName(@Query("appid") String id,
                                                         @Query("q") String param);
    }
}
