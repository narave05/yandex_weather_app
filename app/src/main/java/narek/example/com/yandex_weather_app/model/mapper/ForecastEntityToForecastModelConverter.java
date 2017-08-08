package narek.example.com.yandex_weather_app.model.mapper;


import narek.example.com.yandex_weather_app.db.ForecastEntity;
import narek.example.com.yandex_weather_app.model.clean.City;
import narek.example.com.yandex_weather_app.model.clean.Coords;
import narek.example.com.yandex_weather_app.model.clean.Forecasts;

public class ForecastEntityToForecastModelConverter {
    public Forecasts createForecastFromEntity(ForecastEntity entity){
        return new Forecasts.ForecastsBuilder()
                .dayTemp(entity.getDayTemp())
                .speed(entity.getSpeed())
                .rain(entity.getRain())
                .clouds(entity.getClouds())
                .degrees(entity.getDegrees())
                .humidity(entity.getHumidity())
                .iconId(entity.getIconId())
                .pressure(entity.getPressure())
                .time(entity.getTime())
                .createForecasts();
    }
}
