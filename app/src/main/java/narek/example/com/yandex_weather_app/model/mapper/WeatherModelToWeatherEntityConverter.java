package narek.example.com.yandex_weather_app.model.mapper;


import java.util.Date;

import narek.example.com.yandex_weather_app.db.WeatherEntity;
import narek.example.com.yandex_weather_app.model.clean.Weather;

public class WeatherModelToWeatherEntityConverter {

    public WeatherEntity makeWeatherEntityFromWeather(Weather weather, int cityId){
        WeatherEntity weatherEntity = new WeatherEntity();
        weatherEntity.setCityId(cityId);
        weatherEntity.setLon(weather.getCity().getCoords().getLon());
        weatherEntity.setLat(weather.getCity().getCoords().getLat());
        weatherEntity.setWindSpeed(weather.getWindSpeed());
        weatherEntity.setTemperature(weather.getTemperature());
        weatherEntity.setPressure(weather.getPressure());
        weatherEntity.setHumidity(weather.getHumidity());
        weatherEntity.setCityName(weather.getCity().getName());
        weatherEntity.setConditionCode(weather.getConditionCode());
        weatherEntity.setDate(new Date());
        return weatherEntity;
    }
}
