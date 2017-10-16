package narek.example.com.yandex_weather_app.model.mapper;


import narek.example.com.yandex_weather_app.model.clean.City;
import narek.example.com.yandex_weather_app.model.clean.Coords;
import narek.example.com.yandex_weather_app.model.clean.Forecasts;
import narek.example.com.yandex_weather_app.model.rest.forecast.ForecastRes;
import narek.example.com.yandex_weather_app.model.rest.forecast.ListForecast;

public class ForecastMapper {
    public Forecasts buildForecast(ListForecast listForecast){
        return new Forecasts.ForecastsBuilder()
                .time(listForecast.getDt())
                .clouds(listForecast.getClouds())
                .degrees(listForecast.getDeg())
                .humidity(listForecast.getHumidity())
                .iconId(listForecast.getWeather().get(0).getId())
                .pressure(listForecast.getPressure())
                .rain(listForecast.getRain())
                .speed(listForecast.getSpeed())
                .dayTemp(listForecast.getTemp().getDay())
                .nightTemp(listForecast.getTemp().getNight())
                .createForecasts();
    }
}
