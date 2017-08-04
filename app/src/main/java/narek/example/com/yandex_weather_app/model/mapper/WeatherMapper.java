package narek.example.com.yandex_weather_app.model.mapper;

import java.io.Serializable;

import io.reactivex.annotations.NonNull;
import narek.example.com.yandex_weather_app.model.clean.City;
import narek.example.com.yandex_weather_app.model.clean.Coords;
import narek.example.com.yandex_weather_app.model.clean.Weather;
import narek.example.com.yandex_weather_app.model.rest.WeatherDataRes;

public class WeatherMapper implements Serializable {
    @NonNull
    public Weather transform(@NonNull WeatherDataRes weatherDataRes) {
        City city = new City(weatherDataRes.name,
                new Coords.CoordsBuilder()
                .lat(weatherDataRes.coordRes.lat)
                .lon(weatherDataRes.coordRes.lat)
                .buildCoords());
        return new Weather.WeatherEntityBuilder()
                .city(city)
                .temperature(weatherDataRes.main.temp)
                .pressure(weatherDataRes.main.pressure)
                .humidity(weatherDataRes.main.humidity)
                .windSpeed(weatherDataRes.windRes.speed)
                .conditionCode(weatherDataRes.weatherRes.get(0).id)
                .weatherUpdateDate(weatherDataRes.dt)
                .createWeather();
    }
}
