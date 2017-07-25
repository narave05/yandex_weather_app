package narek.example.com.yandex_weather_app.data;

import java.util.List;

import io.reactivex.Single;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import narek.example.com.yandex_weather_app.data.api.PlacesApi;
import narek.example.com.yandex_weather_app.data.api.WeatherApi;
import narek.example.com.yandex_weather_app.data.preferences.PreferenceHelper;
import narek.example.com.yandex_weather_app.model.clean.SuggestCity;
import narek.example.com.yandex_weather_app.model.clean.Weather;
import narek.example.com.yandex_weather_app.model.mapper.CityMapper;
import narek.example.com.yandex_weather_app.model.mapper.WeatherMapper;
import narek.example.com.yandex_weather_app.model.rest.PlacesResponse;
import narek.example.com.yandex_weather_app.model.rest.WeatherDataRes;

public class RepositoryImpl implements Repository {

    private WeatherApi api = new WeatherApi();
    private PlacesApi placesApi = new PlacesApi();
    private PreferenceHelper preferenceHelper = PreferenceHelper.getInstance();

    private static RepositoryImpl instance;

    public static RepositoryImpl getInstance() {
        if (instance == null) {
            synchronized (RepositoryImpl.class) {
                instance = new RepositoryImpl();
            }
        }
        return instance;
    }

    private RepositoryImpl() {
    }


    @Override
    public Single<Weather> getWeatherData() {
        return api.callWeatherDataByCityCoords(Double.parseDouble(preferenceHelper.getCityLat()),
                Double.parseDouble(preferenceHelper.getCityLon()))
                .subscribeOn(Schedulers.io())
                .map(new Function<WeatherDataRes, Weather>() {
                    @Override
                    public Weather apply(@NonNull WeatherDataRes weatherDataRes) throws Exception {
                        return new WeatherMapper().transform(weatherDataRes);
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
                        return new CityMapper().buildCity(placesResponse);
                    }
                });
    }

    @Override
    public int getCurrentUpdateInterval() {
        return preferenceHelper.getIntervalHoursInSeconds() / 3600;
    }

    @Override
    public void saveUpdateInterval(int currentInterval) {
        preferenceHelper.saveUpdateInterval(currentInterval);
    }
}
