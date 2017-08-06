package narek.example.com.yandex_weather_app.data;

import android.util.Log;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import narek.example.com.yandex_weather_app.App;
import narek.example.com.yandex_weather_app.data.api.PlacesApi;
import narek.example.com.yandex_weather_app.data.api.WeatherApi;
import narek.example.com.yandex_weather_app.data.preferences.PreferenceHelper;
import narek.example.com.yandex_weather_app.db.AppDatabase;
import narek.example.com.yandex_weather_app.db.CityEntity;
import narek.example.com.yandex_weather_app.db.WeatherEntity;
import narek.example.com.yandex_weather_app.model.clean.City;
import narek.example.com.yandex_weather_app.model.clean.Coords;
import narek.example.com.yandex_weather_app.model.clean.SuggestCity;
import narek.example.com.yandex_weather_app.model.clean.Weather;
import narek.example.com.yandex_weather_app.model.mapper.CityModelToCityEntityConverter;
import narek.example.com.yandex_weather_app.model.mapper.CitySuggestionMapper;
import narek.example.com.yandex_weather_app.model.mapper.CoordsMapper;
import narek.example.com.yandex_weather_app.model.mapper.WeatherEntityToWeatherModelConverter;
import narek.example.com.yandex_weather_app.model.mapper.WeatherMapper;
import narek.example.com.yandex_weather_app.model.mapper.WeatherModelToWeatherEntityConverter;
import narek.example.com.yandex_weather_app.model.rest.CoordsResponse;
import narek.example.com.yandex_weather_app.model.rest.PlacesResponse;
import narek.example.com.yandex_weather_app.model.rest.WeatherDataRes;

public class RepositoryImpl implements Repository {

    public static final int SEC = 3600;
    private WeatherApi api = new WeatherApi();
    private PlacesApi placesApi = new PlacesApi();
    private PreferenceHelper preferenceHelper = PreferenceHelper.getInstance();
    private final AppDatabase db;
    private CityEntity cityEntity;
    private WeatherEntity weatherEntity;

    @Inject
    public RepositoryImpl() {
        db = App.getInstance().getAppComponent().provideDb();
    }

    @Override
    public Single<Weather> getWeatherData(final CityEntity cityEntity) {
        return db.weatherDao().loadWeather(cityEntity.getLat(), cityEntity.getLon())
                .map(new Function<WeatherEntity, Weather>() {
                    @Override
                    public Weather apply(@NonNull WeatherEntity weatherEntity) throws Exception {
                        return new WeatherEntityToWeatherModelConverter().makeWeatherFromWeatherEntity(weatherEntity);
                    }
                })
                .concatWith(getWeatherSingleFromInternet(cityEntity))
                .first(new Weather.WeatherEntityBuilder()
                        .createWeather())
                .doOnError(new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        getWeatherSingleFromInternet(cityEntity);
                    }
                });
    }

    private Single<Weather> getWeatherSingleFromInternet(CityEntity cityEntity) {
        return api.callWeatherDataByCityCoords(cityEntity.getLat(), cityEntity.getLon())
                .subscribeOn(Schedulers.io())
                .map(new Function<WeatherDataRes, Weather>() {
                    @Override
                    public Weather apply(@NonNull WeatherDataRes weatherDataRes) throws Exception {
                        return new WeatherMapper().transform(weatherDataRes);
                    }

                })
                .doOnSuccess(new Consumer<Weather>() {
                    @Override
                    public void accept(@NonNull Weather weather) throws Exception {
                        db.weatherDao().insertWeather(new WeatherModelToWeatherEntityConverter().makeWeatherEntityFromWeather(weather));
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
    public void saveUpdateInterval(int currentInterval) {
        preferenceHelper.saveUpdateInterval(currentInterval);
    }

    @Override
    public void setCityInDb(final City city) {
        cityEntity = new CityModelToCityEntityConverter().makeCityFromCityEntity(city);

        Completable.fromAction(new Action() {
            @Override
            public void run() throws Exception {
                db.cityDao().insertCity(cityEntity);
                Log.d(this.getClass().getName(), "run: insert is done");
            }
        })
                .concatWith(Completable.fromAction(new Action() {
                    @Override
                    public void run() throws Exception {
                        cityEntity = db.cityDao().getActiveCityEntity();
                    }
                }))

                .concatWith(Completable.fromAction(new Action() {
                    @Override
                    public void run() throws Exception {
                        if (cityEntity != null) {
                            db.cityDao().deactivateCity(cityEntity.getCityName());
                        }

                    }
                }))
                .concatWith(Completable.fromAction(new Action() {
                    @Override
                    public void run() throws Exception {

                        db.cityDao().updateCurrentCity(city.getName(), true, city.getCoords().getLat(),
                                city.getCoords().getLon());

                    }
                }))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();
    }

    @Override
    //onError go to the internet
    public Single<CityEntity> getActiveCityFromDb() {
        return db.cityDao().getActiveSingleCity()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Flowable<CityEntity> getCityFromDb() {
        return db.cityDao().loadActiveFlowableCity()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public void setWeather(Weather weather) {
        weatherEntity = new WeatherModelToWeatherEntityConverter().makeWeatherEntityFromWeather(weather);

        Completable.fromAction(new Action() {
            @Override
            public void run() throws Exception {
                db.weatherDao().insertWeather(weatherEntity);
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();
    }

    @Override
    public Flowable<List<WeatherEntity>> getAllWeather() {
        return db.weatherDao().loadAll()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Single<WeatherEntity> getWeather(City city) {
        return db.weatherDao().loadWeather(city.getCoords().getLat(), city.getCoords().getLon())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public void deleteCity(City city) {
        cityEntity = new CityModelToCityEntityConverter().makeCityFromCityEntity(city);
        Completable.fromAction(new Action() {
            @Override
            public void run() throws Exception {
                db.cityDao().deleteCity(cityEntity.getCityName());
            }
        })
                .subscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe();
    }

    @Override
    public void updateCity(City city) {

    }
}
