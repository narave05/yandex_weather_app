package narek.example.com.yandex_weather_app.data;

import io.reactivex.Single;
import narek.example.com.yandex_weather_app.model.clean.Weather;

public interface Repository {
    Single<Weather> getWeatherData();
}
