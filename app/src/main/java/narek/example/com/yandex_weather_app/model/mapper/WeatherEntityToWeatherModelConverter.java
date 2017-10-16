package narek.example.com.yandex_weather_app.model.mapper;


import java.util.Date;

import narek.example.com.yandex_weather_app.db.WeatherEntity;
import narek.example.com.yandex_weather_app.model.clean.City;
import narek.example.com.yandex_weather_app.model.clean.Coords;
import narek.example.com.yandex_weather_app.model.clean.Weather;

public class WeatherEntityToWeatherModelConverter {

    public Weather makeWeatherFromWeatherEntity(WeatherEntity weatherEntity) {
        return new Weather.WeatherEntityBuilder()
                .city(new City.CityBuilder()
                .name(weatherEntity.getCityName())
                .coords(new Coords.CoordsBuilder()
                .lat(weatherEntity.getLat())
                .lon(weatherEntity.getLon())
                .buildCoords())
                .createCity())
                .conditionCode(weatherEntity.getConditionCode())
                .humidity(weatherEntity.getHumidity())
                .pressure(weatherEntity.getPressure())
                .temperature(weatherEntity.getTemperature())
                .weatherUpdateDate(System.currentTimeMillis())
                .windSpeed(weatherEntity.getWindSpeed())
                .createWeather();
    }
}
