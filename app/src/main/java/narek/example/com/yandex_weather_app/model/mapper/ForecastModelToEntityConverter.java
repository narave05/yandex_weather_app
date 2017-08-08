package narek.example.com.yandex_weather_app.model.mapper;


import narek.example.com.yandex_weather_app.db.ForecastEntity;
import narek.example.com.yandex_weather_app.model.clean.Forecasts;

public class ForecastModelToEntityConverter {
    public ForecastEntity createForecastEntityFromModel(Forecasts forecast, int cityId) {
        ForecastEntity forecastEntity = new ForecastEntity();
        forecastEntity.setCityId(cityId);
        forecastEntity.setClouds(forecast.getClouds());
        forecastEntity.setDayTemp(forecast.getDayTemp());
        forecastEntity.setDegrees(forecast.getDegrees());
        forecastEntity.setHumidity(forecast.getHumidity());
        forecastEntity.setPressure(forecast.getPressure());
        forecastEntity.setRain(forecast.getRain());
        forecastEntity.setIconId(forecast.getIconId());
        forecastEntity.setSpeed(forecast.getSpeed());
        forecastEntity.setTime(forecast.getTime());
        return forecastEntity;
    }
}
