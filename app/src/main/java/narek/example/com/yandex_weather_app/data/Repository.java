package narek.example.com.yandex_weather_app.data;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.Single;
import narek.example.com.yandex_weather_app.db.CityEntity;
import narek.example.com.yandex_weather_app.model.clean.City;
import narek.example.com.yandex_weather_app.model.clean.Coords;
import narek.example.com.yandex_weather_app.model.clean.SuggestCity;
import narek.example.com.yandex_weather_app.model.clean.Weather;
import narek.example.com.yandex_weather_app.model.rest.PlacesResponse;

public interface Repository {

    Single<Weather> getWeatherData();

    Single<List<SuggestCity>> getPlacesSuggestion(String text);

    Single<Coords> callForCityCoords(String cityId);

    int getCurrentUpdateInterval();

    void saveUpdateInterval(int currentInterval);

    void setCityInDb(City city);

    Flowable<CityEntity> getCityFromDb();

    void setWeather(Weather weather);

    List<Weather> getAllWeather();

    Weather getWeather(City city);

    void deleteCity(City city);

    void updateCity(City city);


}