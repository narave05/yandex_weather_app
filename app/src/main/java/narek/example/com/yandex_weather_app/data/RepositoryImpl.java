package narek.example.com.yandex_weather_app.data;

import android.arch.persistence.room.Room;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import narek.example.com.yandex_weather_app.App;
import narek.example.com.yandex_weather_app.data.api.PlacesApi;
import narek.example.com.yandex_weather_app.data.api.WeatherApi;
import narek.example.com.yandex_weather_app.data.preferences.PreferenceHelper;
import narek.example.com.yandex_weather_app.db.AppDatabase;
import narek.example.com.yandex_weather_app.db.CityEntity;
import narek.example.com.yandex_weather_app.model.clean.City;
import narek.example.com.yandex_weather_app.model.clean.Coords;
import narek.example.com.yandex_weather_app.model.clean.SuggestCity;
import narek.example.com.yandex_weather_app.model.clean.Weather;
import narek.example.com.yandex_weather_app.model.mapper.CityEntityFromCityModelMapper;
import narek.example.com.yandex_weather_app.model.mapper.CitySuggestionMapper;
import narek.example.com.yandex_weather_app.model.mapper.CoordsMapper;
import narek.example.com.yandex_weather_app.model.mapper.WeatherEntityFromWeatherModelMapper;
import narek.example.com.yandex_weather_app.model.mapper.WeatherMapper;
import narek.example.com.yandex_weather_app.model.rest.CoordsResponse;
import narek.example.com.yandex_weather_app.model.rest.PlacesResponse;
import narek.example.com.yandex_weather_app.model.rest.WeatherDataRes;

public class RepositoryImpl implements Repository {

    public static final int SEC = 3600;
    private WeatherApi api = new WeatherApi();
    private PlacesApi placesApi = new PlacesApi();
    private PreferenceHelper preferenceHelper = PreferenceHelper.getInstance();
    private final AppDatabase db;

    @Inject
    public RepositoryImpl() {
        db = Room.databaseBuilder(App.getInstance(),
                AppDatabase.class, "database-name").build();
    }

    @Override
    public Single<Weather> getWeatherData() {
        return api.callWeatherDataByCityCoords(Double.parseDouble(preferenceHelper.getCityLat()),
                Double.parseDouble(preferenceHelper.getCityLon()))
                .subscribeOn(Schedulers.io())
                .map(new Function<WeatherDataRes, Weather>() {
                    @Override
                    public Weather apply(@NonNull WeatherDataRes weatherDataRes) throws Exception {
                        Weather weather = new WeatherMapper().transform(weatherDataRes);
                        setCity(weather.getCity());
                        return weather;
                    }
                });
    }

    @Override
    public Single<List<SuggestCity>> getPlacesSuggestion(String text) {
        return placesApi.callPlacesSuggestions(text)
                .subscribeOn(Schedulers.io())
                .map(new Function<PlacesResponse, List<SuggestCity>>() {
                    @Override
                    public List<SuggestCity> apply(@NonNull PlacesResponse placesResponse) throws Exception {
                        return new CitySuggestionMapper().buildCity(placesResponse);
                    }
                });
    }

    @Override
    public Single<Coords> callForCityCoords(String cityId) {
        return placesApi.callCordsByCityId(cityId)
                .subscribeOn(Schedulers.io())
                .map(new Function<CoordsResponse, Coords>() {
                    @Override
                    public Coords apply(@NonNull CoordsResponse coordsResponse) throws Exception {
                        return new CoordsMapper().builCoords(coordsResponse);
                    }
                });
    }



    @Override
    public int getCurrentUpdateInterval() {
        return preferenceHelper.getIntervalHoursInSeconds() / SEC;
    }

    @Override
    public void saveCityCoords(double lat, double lon) {
        preferenceHelper.saveCityLat(String.valueOf(lat));
        preferenceHelper.saveCityLon(String.valueOf(lon));
    }

    @Override
    public Coords getCityCoords() {
        return new Coords.CoordsBuilder()
                .lat(Double.parseDouble(preferenceHelper.getCityLat()))
                .lon(Double.parseDouble(preferenceHelper.getCityLon()))
                .buildCoords();
    }

    @Override
    public void saveUpdateInterval(int currentInterval) {
        preferenceHelper.saveUpdateInterval(currentInterval);
    }

    @Override
    public void setCity(City city) {
        CityEntity cityEntity = new CityEntity();
        cityEntity.setActive(true);
        cityEntity.setCityName(city.getName());
        cityEntity.setCoords(city.getCoords());
        cityEntity.setCityPlaceId(city.getCityPlaceId());
        db.cityDao().insertCity(cityEntity);
    }

    @Override
    public City getCity() {
        return null;
    }

    @Override
    public void setWeather(Weather weather) {
        db.weatherDao().insertWeather();
    }

    @Override
    public List<Weather> getAllWeather() {
        return null;
    }

    @Override
    public Weather getWeather(City city) {
        return null;
    }

    @Override
    public void deleteCity(City city) {

    }

    @Override
    public void updateCity(City city) {

    }
}
