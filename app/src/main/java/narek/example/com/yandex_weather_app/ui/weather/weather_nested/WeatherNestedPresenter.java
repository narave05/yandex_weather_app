package narek.example.com.yandex_weather_app.ui.weather.weather_nested;


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
import narek.example.com.yandex_weather_app.ui.weather.WeatherFragmentPresenter;
import narek.example.com.yandex_weather_app.util.NetworkStatusChecker;
import narek.example.com.yandex_weather_app.util.UnitsConverter;

@InjectViewState
public class WeatherNestedPresenter extends MvpBasePresenter<WeatherNestedView> {

    private CityEntity currentCityEntity;

    private Repository repository;

    @Inject
    public WeatherNestedPresenter(Repository repository) {
        this.repository = repository;
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
                        loadForecast(cityEntity);
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
                                    Log.d(WeatherNestedPresenter.this.getClass().getName(), "accept: " + throwable.getMessage());
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
                                }
                            }, new Consumer<Throwable>() {
                                @Override
                                public void accept(@NonNull Throwable throwable) throws Exception {
                                    getViewState().showError(R.string.data_not_updated);

                                }
                            })
            );
        } else {
            getViewState().showError(R.string.data_not_updated);

        }
    }
}
