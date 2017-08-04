package narek.example.com.yandex_weather_app.model.mapper;


import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import narek.example.com.yandex_weather_app.db.CityEntity;
import narek.example.com.yandex_weather_app.db.WeatherEntity;
import narek.example.com.yandex_weather_app.model.clean.City;
import narek.example.com.yandex_weather_app.model.clean.Weather;

public class WeatherEntityFromWeatherModelMapper {

    @NonNull
    public WeatherEntity transformWeatherEntityFromWeatherModel(@NonNull Weather weather) {
        List<CityEntity> cityEntityList = new ArrayList<>();
        cityEntityList.add(new CityEntity.CityEntityBuilder()
        .cityEntityName(weather.getCity().getName())
        .cityEntityCoords(weather.getCity().getCoords())
        .isActive(false)
        .createCityEntity());

        return new WeatherEntity.WeatherEntityBuilder()
                .id(0)
                .pressure(weather.getPressure())
                .conditionCode(weather.getConditionCode())
                .city(cityEntityList)
                .cityName(weather.getCity().getName())
                .humidity(weather.getHumidity())
                .temperature(weather.getTemperature())
                .windSpeed(weather.getWindSpeed())
                .weatherUpdateDate(weather.getDate().getTime())
                .createWeatherEntity();

    }
}
