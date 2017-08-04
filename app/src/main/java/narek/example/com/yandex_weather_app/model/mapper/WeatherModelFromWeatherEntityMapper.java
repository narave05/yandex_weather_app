package narek.example.com.yandex_weather_app.model.mapper;


import android.support.annotation.NonNull;

import narek.example.com.yandex_weather_app.db.WeatherEntity;
import narek.example.com.yandex_weather_app.model.clean.City;
import narek.example.com.yandex_weather_app.model.clean.Weather;

public class WeatherModelFromWeatherEntityMapper {
    @NonNull
    public Weather transformWeatherModelFromWeatherEntity(WeatherEntity weatherEntity){
        City city = new City.CityBuilder()
                .name(weatherEntity.getCityEntity().get(0).getCityName())
                .coords(weatherEntity.getCityEntity().get(0).getCoords())
                .createCity();

        return new Weather.WeatherEntityBuilder()
                .temperature(weatherEntity.getTemperature())
                .windSpeed(weatherEntity.getWindSpeed())
                .weatherUpdateDate(weatherEntity.getDate().getTime())
                .humidity(weatherEntity.getHumidity())
                .city(city)
                .conditionCode(weatherEntity.getConditionCode())
                .pressure(weatherEntity.getPressure())
                .createWeather();
    }
}
