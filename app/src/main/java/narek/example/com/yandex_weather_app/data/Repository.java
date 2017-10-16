package narek.example.com.yandex_weather_app.data;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Single;
import narek.example.com.yandex_weather_app.db.CityEntity;
import narek.example.com.yandex_weather_app.db.ForecastEntity;
import narek.example.com.yandex_weather_app.db.WeatherEntity;
import narek.example.com.yandex_weather_app.model.clean.City;
import narek.example.com.yandex_weather_app.model.clean.Coords;
import narek.example.com.yandex_weather_app.model.clean.Forecasts;
import narek.example.com.yandex_weather_app.model.clean.SuggestCity;
import narek.example.com.yandex_weather_app.model.clean.Weather;

public interface Repository {

    Single<Weather> getWeatherData(CityEntity cityEntity);

    Single<Weather> getWeatherSingleFromInternet(CityEntity cityEntity);

    Single<List<SuggestCity>> getPlacesSuggestion(String text);

    Single<Coords> callForCityCoords(String cityId);

    int getCurrentUpdateInterval();

    void saveUpdateInterval(int currentInterval);

    void setCityInDb(City city);

    Flowable<CityEntity> getCityFromDb();

    void setWeather(Weather weather);

    void deleteCity(CityEntity city);

    Single<CityEntity> getActiveCityFromDb();

    void getActiveCityEntity();

    Single<List<Forecasts>> getForecast(CityEntity cityEntity);

    Single<List<Forecasts>> getForecastFromInternet(CityEntity cityEntity);

    void insertForecastInDb(List<ForecastEntity> list);

    Flowable<List<CityEntity>> getAllCitiesFlowable();

    void updateActiveCity(CityEntity city);


}