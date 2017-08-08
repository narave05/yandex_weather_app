package narek.example.com.yandex_weather_app.ui.weather;

import android.support.v4.app.DialogFragment;
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
import narek.example.com.yandex_weather_app.data.Repository;
import narek.example.com.yandex_weather_app.db.CityEntity;
import narek.example.com.yandex_weather_app.model.clean.Forecasts;
import narek.example.com.yandex_weather_app.model.clean.Weather;
import narek.example.com.yandex_weather_app.ui._common.base.MvpBasePresenter;
import narek.example.com.yandex_weather_app.ui.find_city.FindCityFragment;
import narek.example.com.yandex_weather_app.util.NetworkStatusChecker;
import narek.example.com.yandex_weather_app.util.UnitsConverter;

@InjectViewState
public class WeatherFragmentPresenter extends MvpBasePresenter<WeatherFragmentView> {

    private Repository repository;
    private DialogFragment suggestDialog;
    private CityEntity currentCityEntity;
    private UnitsConverter unitsConverter = new UnitsConverter();

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
                        loadWeather(cityEntity);
                        loadForecast(cityEntity);
                        if (suggestDialog != null) {
                            getViewState().dismissDialog(suggestDialog);
                        }
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
                                   loadWeather(cityEntity);
                                   loadForecast(cityEntity);
                               }
                           },
                        new Consumer<Throwable>() {
                            @Override
                            public void accept(@NonNull Throwable throwable) throws Exception {
                                suggestDialog = FindCityFragment.newInstance();
                                getViewState().showDialogCitySuggest(suggestDialog);
                            }
                        }));
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

    void loadWeather(CityEntity cityEntity) {
        if (NetworkStatusChecker.isNetworkAvailable()) {
            compositeDisposable.add(
                    repository.getWeatherData(cityEntity)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(new Consumer<Weather>() {
                                @Override
                                public void accept(@NonNull Weather weather) throws Exception {
                                    sendWeatherData(weather);
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

    private void sendWeatherData(@Nullable Weather weather) {
        if (weather != null) {
            weather.setTemperature(unitsConverter.convertTemperature(weather.getTemperature()));
            getViewState().showWeather(weather);
        }
    }
    private void sendForecastData(@Nullable List<Forecasts> forecast) {
        if (forecast != null) {
            getViewState().showForecast(forecast);
        }
    }

    public void loadWeatherFromInternet() {
        if (NetworkStatusChecker.isNetworkAvailable()) {
            compositeDisposable.add(
                    repository.getWeatherSingleFromInternet(currentCityEntity)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(new Consumer<Weather>() {
                                @Override
                                public void accept(@NonNull Weather weather) throws Exception {
                                    getViewState().hideSwipeRefresh();
                                    sendWeatherData(weather);
                                }
                            }, new Consumer<Throwable>() {
                                @Override
                                public void accept(@NonNull Throwable throwable) throws Exception {
                                    getViewState().showError(R.string.data_not_updated);
                                    getViewState().hideSwipeRefresh();

                                }
                            })
            );
        } else {
            getViewState().hideSwipeRefresh();
            getViewState().showError(R.string.data_not_updated);

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
                                    getViewState().hideSwipeRefresh();
                                    sendForecastData(forecast);
                                }
                            }, new Consumer<Throwable>() {
                                @Override
                                public void accept(@NonNull Throwable throwable) throws Exception {
                                    getViewState().showError(R.string.data_not_updated);
                                    getViewState().hideSwipeRefresh();

                                }
                            })
            );
        } else {
            getViewState().hideSwipeRefresh();
            getViewState().showError(R.string.data_not_updated);

        }
    }
}
