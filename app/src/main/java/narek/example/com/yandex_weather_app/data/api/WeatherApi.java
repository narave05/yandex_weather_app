package narek.example.com.yandex_weather_app.data.api;

import android.arch.persistence.room.ForeignKey;

import io.reactivex.Single;
import narek.example.com.yandex_weather_app.model.rest.WeatherDataRes;
import narek.example.com.yandex_weather_app.model.rest.forecast.ForecastRes;
import narek.example.com.yandex_weather_app.util.AppConfig;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class WeatherApi {

    private WeatherRestService weatherRestService = RestServiceGenerator.createService(WeatherRestService.class,
            AppConfig.BASE_URL);

    public Single<WeatherDataRes> callWeatherDataByCityCoords(double cityLat, double cityLon) {
        return weatherRestService.callWeatherDataByCityCoords(AppConfig.API_ID, cityLat, cityLon);
    }
    public Single<ForecastRes> getForecastFromApi(double lat, double lon){
        return weatherRestService.callForecast(AppConfig.API_ID, lat, lon);
    }

    private interface WeatherRestService {
        @GET("weather")
        Single<WeatherDataRes> callWeatherDataByCityCoords(@Query("appid") String id,
                                                           @Query("lat") double lat, @Query("lon") double lon);

        @GET("forecast/daily")
        Single<ForecastRes> callForecast (@Query("appid") String id, @Query("lat") double lat, @Query("lon") double lon);
    }

}
