package narek.example.com.yandex_weather_app.data;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;
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

    void saveCityCoords(double lat, double lon);

    Coords getCityCoords();

    void saveUpdateInterval(int currentInterval);

    void setCity(City city);

    City getCity();

    void setWeather(Weather weather);

    List<Weather> getAllWeather();

    Weather getWeather(City city);

    void deleteCity(City city);

    void updateCity(City city);


}