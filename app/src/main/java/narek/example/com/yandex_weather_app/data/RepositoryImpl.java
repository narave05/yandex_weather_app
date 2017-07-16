package narek.example.com.yandex_weather_app.data;

import android.util.Log;

import io.reactivex.Single;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import narek.example.com.yandex_weather_app.data.api.WeatherApi;
import narek.example.com.yandex_weather_app.model.clean.City;
import narek.example.com.yandex_weather_app.model.clean.Weather;
import narek.example.com.yandex_weather_app.model.rest.WeatherDataRes;

public class RepositoryImpl implements Repository {

    private WeatherApi api = new WeatherApi();

    @Override
    public Single<Weather> getWeatherData() {
        return api.callWeatherDataByCityName("moscow,ru")
                .subscribeOn(Schedulers.io())
                .map(new Function<WeatherDataRes, Weather>() {
                    @Override
                    public Weather apply(@NonNull WeatherDataRes weatherDataRes) throws Exception {
                        Log.e("getWeatherData", "apply: " + String.valueOf(weatherDataRes == null));
                        return weatherResConvertToWeatherClean(weatherDataRes);
                    }
                });
    }

    @android.support.annotation.NonNull
    private Weather weatherResConvertToWeatherClean(@NonNull WeatherDataRes weatherDataRes) {
        City city = new City(weatherDataRes.name,
                weatherDataRes.coordRes.lon,
                weatherDataRes.coordRes.lat);
        return new Weather(city,
                weatherDataRes.main.temp,
                weatherDataRes.main.pressure,
                weatherDataRes.main.humidity,
                weatherDataRes.windRes.speed);
    }
}
