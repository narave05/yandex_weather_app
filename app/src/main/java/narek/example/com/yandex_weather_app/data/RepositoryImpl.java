package narek.example.com.yandex_weather_app.data;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Single;
import io.reactivex.SingleSource;
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
import narek.example.com.yandex_weather_app.db.ForecastEntity;
import narek.example.com.yandex_weather_app.db.WeatherEntity;
import narek.example.com.yandex_weather_app.model.clean.City;
import narek.example.com.yandex_weather_app.model.clean.Coords;
import narek.example.com.yandex_weather_app.model.clean.Forecasts;
import narek.example.com.yandex_weather_app.model.clean.SuggestCity;
import narek.example.com.yandex_weather_app.model.clean.Weather;
import narek.example.com.yandex_weather_app.model.mapper.CityEntityToCityModelConverter;
import narek.example.com.yandex_weather_app.model.mapper.CityModelToCityEntityConverter;
import narek.example.com.yandex_weather_app.model.mapper.CitySuggestionMapper;
import narek.example.com.yandex_weather_app.model.mapper.CoordsMapper;
import narek.example.com.yandex_weather_app.model.mapper.ForecastEntityToForecastModelConverter;
import narek.example.com.yandex_weather_app.model.mapper.ForecastMapper;
import narek.example.com.yandex_weather_app.model.mapper.ForecastModelToEntityConverter;
import narek.example.com.yandex_weather_app.model.mapper.WeatherEntityToWeatherModelConverter;
import narek.example.com.yandex_weather_app.model.mapper.WeatherMapper;
import narek.example.com.yandex_weather_app.model.mapper.WeatherModelToWeatherEntityConverter;
import narek.example.com.yandex_weather_app.model.rest.CoordsResponse;
import narek.example.com.yandex_weather_app.model.rest.PlacesResponse;
import narek.example.com.yandex_weather_app.model.rest.WeatherDataRes;
import narek.example.com.yandex_weather_app.model.rest.forecast.ForecastRes;
import narek.example.com.yandex_weather_app.model.rest.forecast.ListForecast;

public class RepositoryImpl implements Repository {

    public static final int SEC = 3600;
    private WeatherApi api = new WeatherApi();
    private PlacesApi placesApi = new PlacesApi();
    private PreferenceHelper preferenceHelper = PreferenceHelper.getInstance();
    private AppDatabase db;
    private CityEntity cityEntity;
    private WeatherEntity weatherEntity;
    private CityEntity activeCityEntity;


    public RepositoryImpl() {
        db = App.getInstance().getAppComponent().provideDb();
        getActiveCityEntity();
    }

    @Override
    public Single<Weather> getWeatherData(final CityEntity cityEntity) {
        return db.weatherDao().loadWeather(cityEntity.getCityId())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Function<WeatherEntity, Weather>() {
                    @Override
                    public Weather apply(@NonNull WeatherEntity weatherEntity) throws Exception {
                        return new WeatherEntityToWeatherModelConverter().makeWeatherFromWeatherEntity(weatherEntity);
                    }
                })

                .onErrorResumeNext(new Function<Throwable, SingleSource<? extends Weather>>() {
                    @Override
                    public SingleSource<? extends Weather> apply(@NonNull Throwable throwable) throws Exception {
                        return getWeatherSingleFromInternet(cityEntity);
                    }
                });
    }

    @Override
    public Single<List<Forecasts>> getForecast(final CityEntity cityEntity) {
        return db.forecastDao().loadForecast(cityEntity.getCityId())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Function<List<ForecastEntity>, List<Forecasts>>() {
                    @Override
                    public List<Forecasts> apply(@NonNull List<ForecastEntity> forecastEntities) throws Exception {
                        List<Forecasts> listForecasts = null;
                        if (forecastEntities.size() != 0) {
                            listForecasts = new ArrayList<Forecasts>();
                        }
                        for (int i = 0; i < forecastEntities.size(); i++) {

                            listForecasts.add(new ForecastEntityToForecastModelConverter().createForecastFromEntity(forecastEntities.get(i)));
                        }

                        return listForecasts;
                    }
                })
                .onErrorResumeNext(new Function<Throwable, SingleSource<? extends List<Forecasts>>>() {
                    @Override
                    public SingleSource<? extends List<Forecasts>> apply(@NonNull Throwable throwable) throws Exception {
                        Log.d(RepositoryImpl.this.getClass().getName(), "accept: onErrorResumeNext forcast done");
                        return getForecastFromInternet(cityEntity);
                    }
                });
    }

    @Override
    public Single<Weather> getWeatherSingleFromInternet(final CityEntity cityEntity) {
        return api.callWeatherDataByCityCoords(cityEntity.getLat(), cityEntity.getLon())
                .subscribeOn(Schedulers.io())
                .map(new Function<WeatherDataRes, Weather>() {
                    @Override
                    public Weather apply(@NonNull WeatherDataRes weatherDataRes) throws Exception {
                        return new WeatherMapper().transform(weatherDataRes);
                    }
                }).doOnSuccess(new Consumer<Weather>() {
                    @Override
                    public void accept(@NonNull Weather weather) throws Exception {
                        setWeather(weather);
                    }
                });
    }

    @Override
    public Single<List<Forecasts>> getForecastFromInternet(CityEntity cityEntity) {
        return api.getForecastFromApi(cityEntity.getLat(), cityEntity.getLon())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Function<ForecastRes, List<Forecasts>>() {
                    @Override
                    public List<Forecasts> apply(@NonNull ForecastRes forecastRes) throws Exception {
                        List<Forecasts> list = new ArrayList<>();
                        for (ListForecast lf : forecastRes.getList()) {
                            list.add(new ForecastMapper().buildForecast(lf));
                        }
                        return list;
                    }
                })
                .doOnSuccess(new Consumer<List<Forecasts>>() {
                    @Override
                    public void accept(@NonNull List<Forecasts> forecast) throws Exception {
                        List<ForecastEntity> entityList = new ArrayList<>();
                        for (Forecasts f : forecast) {
                            entityList.add(new ForecastModelToEntityConverter()
                                    .createForecastEntityFromModel(f, activeCityEntity.getCityId()));
                        }
                        insertForecastInDb(entityList);

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
                        if (activeCityEntity != null) {
                            db.cityDao().deactivateCity(activeCityEntity.getCityName());
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
                .concatWith(Completable.fromAction(new Action() {
                    @Override
                    public void run() throws Exception {
                        getActiveCityEntity();
                    }
                }))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();
    }

    @Override
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
        weatherEntity = new WeatherModelToWeatherEntityConverter().makeWeatherEntityFromWeather(weather, activeCityEntity.getCityId());

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
        return db.weatherDao().loadWeather(activeCityEntity.getCityId())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public void deleteCity(final CityEntity city) {
        Completable.fromAction(new Action() {
            @Override
            public void run() throws Exception {
                db.cityDao().deleteCity(city.getCityName(), city.getLat(), city.getLon());
            }
        })
                .subscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe();
    }

    @Override
    public void updateCity(City city) {

    }

    @Override
    public void insertForecastInDb(final List<ForecastEntity> list) {
        Completable.fromAction(new Action() {
            @Override
            public void run() throws Exception {
                db.forecastDao().insertForecast(list);
            }
        }).subscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe();
    }

    @Override
    public void getActiveCityEntity() {
        Completable.fromAction(new Action() {
            @Override
            public void run() throws Exception {
                activeCityEntity = db.cityDao().getActiveCityEntity();
            }
        }).subscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe();
    }

    @Override
    public Flowable<List<CityEntity>> getAllCitiesFlowable() {
        return db.cityDao().getAllCities()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public void updateActiveCity(final CityEntity city) {
        Completable.fromAction(new Action() {
            @Override
            public void run() throws Exception {
                db.cityDao().deactivateCity(activeCityEntity.getCityName());
            }
        })
                .andThen(Completable.fromAction(new Action() {
                    @Override
                    public void run() throws Exception {
                        db.cityDao().updateCurrentCity(city.getCityName(), true, city.getLat(), city.getLon());
                    }
                }))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();

        getActiveCityEntity();
    }
}
