package narek.example.com.yandex_weather_app.model.mapper;


import java.util.ArrayList;
import java.util.List;

import narek.example.com.yandex_weather_app.model.clean.Forecasts;
import narek.example.com.yandex_weather_app.model.rest.forecast.ListForecast;

public class ForecastsListMapper {

    public List<Forecasts> buildForecastList(List<ListForecast> resList) {
        List<Forecasts> list = new ArrayList<>();

        for (ListForecast forcasts : resList) {

            list.add(new Forecasts.ForecastsBuilder()
                    .time(forcasts.getDt())
                    .dayTemp(forcasts.getTemp().getDay())
                    .nightTemp(forcasts.getTemp().getNight())
                    .clouds(forcasts.getClouds())
                    .degrees(forcasts.getDeg())
                    .humidity(forcasts.getHumidity())
                    .pressure(forcasts.getPressure())
                    .rain(forcasts.getRain())
                    .speed(forcasts.getSpeed())
                    .createForecasts());
        }
        return list;
    }
}
