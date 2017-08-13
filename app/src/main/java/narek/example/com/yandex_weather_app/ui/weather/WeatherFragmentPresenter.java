package narek.example.com.yandex_weather_app.ui.weather;

import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;

import com.arellomobile.mvp.InjectViewState;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.annotations.Nullable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import narek.example.com.yandex_weather_app.R;
import narek.example.com.yandex_weather_app.adapter.ViewPagerAdapter;
import narek.example.com.yandex_weather_app.data.Repository;
import narek.example.com.yandex_weather_app.db.CityEntity;
import narek.example.com.yandex_weather_app.model.clean.Forecasts;
import narek.example.com.yandex_weather_app.model.clean.Weather;
import narek.example.com.yandex_weather_app.ui._common.base.MvpBasePresenter;
import narek.example.com.yandex_weather_app.ui.find_city.FindCityFragment;
import narek.example.com.yandex_weather_app.ui.weather.cities_nested.CitiesNestedFragment;
import narek.example.com.yandex_weather_app.ui.weather.weather_nested.WeatherNestedFragment;
import narek.example.com.yandex_weather_app.ui.weather.weather_nested.WeatherNestedPresenter;
import narek.example.com.yandex_weather_app.util.NetworkStatusChecker;
import narek.example.com.yandex_weather_app.util.UnitsConverter;

@InjectViewState
public class WeatherFragmentPresenter extends MvpBasePresenter<WeatherFragmentView> {

    private Repository repository;
    private UnitsConverter unitsConverter = new UnitsConverter();
    private DialogFragment suggestFragment;
    private CityEntity currentCityEntity;


    @Inject
    public WeatherFragmentPresenter(Repository repository) {
        this.repository = repository;
        getCityFromDb();
        subscribeToNewCityFromDb();
    }
    private void subscribeToNewCityFromDb() {
        compositeDisposable.add(repository.getCityFromDb()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<CityEntity>() {
                    @Override
                    public void accept(@NonNull CityEntity cityEntity) throws Exception {
                        currentCityEntity = cityEntity;
                        if (suggestFragment != null) {
                            getViewState().closeCitiesFragment(suggestFragment);
                        }

                        loadWeather(cityEntity);
                        loadForecast(cityEntity);
                    }
                }));
    }
    public void getCityFromDb(){
        compositeDisposable.add(repository.getActiveCityFromDb()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<CityEntity>() {
                               @Override
                               public void accept(@NonNull CityEntity cityEntity) throws Exception {
                                   currentCityEntity = cityEntity;

                               }
                           },
                        new Consumer<Throwable>() {
                            @Override
                            public void accept(@NonNull Throwable throwable) throws Exception {
                                suggestFragment = FindCityFragment.newInstance();
                                getViewState().openCitiesFragment(suggestFragment);

                            }
                        }));
    }
    void loadWeather(final CityEntity cityEntity) {
        if (NetworkStatusChecker.isNetworkAvailable()) {
            compositeDisposable.add(
                    repository.getWeatherData(cityEntity)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(new Consumer<Weather>() {
                                @Override
                                public void accept(@NonNull Weather weather) throws Exception {
                                    sendWeatherData(weather, cityEntity.getCityName());
                                }
                            }, new Consumer<Throwable>() {
                                @Override
                                public void accept(@NonNull Throwable throwable) throws Exception {
                                    Log.d(WeatherFragmentPresenter.this.getClass().getName(), "accept: " + throwable.getMessage());
                                    getViewState().showError(R.string.data_not_updated);

                                }
                            })
            );

        } else {
            getViewState().showError(R.string.data_not_updated);

        }
    }

    public void loadWeatherFromInternet() {
        if (currentCityEntity != null) {
            if (NetworkStatusChecker.isNetworkAvailable()) {
                compositeDisposable.add(
                        repository.getWeatherSingleFromInternet(currentCityEntity)
                                .subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(new Consumer<Weather>() {
                                    @Override
                                    public void accept(@NonNull Weather weather) throws Exception {
                                        sendWeatherData(weather, currentCityEntity.getCityName());
                                        getViewState().hideSwipe();
                                    }
                                }, new Consumer<Throwable>() {
                                    @Override
                                    public void accept(@NonNull Throwable throwable) throws Exception {
                                        getViewState().showError(R.string.data_not_updated);
                                        getViewState().hideSwipe();
                                    }
                                })
                );
            } else {
                getViewState().showError(R.string.data_not_updated);

            }
        }

    }

    private void sendWeatherData(@Nullable Weather weather, String cityName) {
        if (weather != null) {
            getViewState().showWeather(weather, cityName);
        }
    }
    private void loadForecast(CityEntity cityEntity) {
        if (NetworkStatusChecker.isNetworkAvailable()) {
            compositeDisposable.add(
                    repository.getForecast(cityEntity)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(new Consumer<List<Forecasts>>() {
                                @Override
                                public void accept(@NonNull List<Forecasts> forecast) throws Exception {
                                    sendForecastData(forecast);
                                }
                            }, new Consumer<Throwable>() {
                                @Override
                                public void accept(@NonNull Throwable throwable) throws Exception {
                                    Log.d(WeatherFragmentPresenter.this.getClass().getName(), "accept: " + throwable.getMessage());
                                    getViewState().showError(R.string.data_not_updated);
                                }
                            })
            );

        } else {
            getViewState().showError(R.string.data_not_updated);
        }
    }




    private void sendForecastData(@Nullable List<Forecasts> forecast) {
        if (forecast != null) {
            getViewState().showForecast(forecast);
        }
    }


    void loadForecastFromInternet(){
        if (NetworkStatusChecker.isNetworkAvailable()) {
            compositeDisposable.add(
                    repository.getForecastFromInternet(currentCityEntity)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(new Consumer<List<Forecasts>>() {
                                @Override
                                public void accept(@NonNull List<Forecasts> forecast) throws Exception {
                                    sendForecastData(forecast);
                                    getViewState().hideSwipe();
                                }
                            }, new Consumer<Throwable>() {
                                @Override
                                public void accept(@NonNull Throwable throwable) throws Exception {
                                    getViewState().showError(R.string.data_not_updated);
                                    getViewState().hideSwipe();

                                }
                            })
            );
        } else {
            getViewState().showError(R.string.data_not_updated);

        }
    }
}
