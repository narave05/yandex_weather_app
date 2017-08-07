package narek.example.com.yandex_weather_app.ui.weather;

import android.support.v4.app.DialogFragment;

import com.arellomobile.mvp.InjectViewState;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.annotations.Nullable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import narek.example.com.yandex_weather_app.R;
import narek.example.com.yandex_weather_app.data.Repository;
import narek.example.com.yandex_weather_app.data.locale.WeatherStorage;
import narek.example.com.yandex_weather_app.db.CityEntity;
import narek.example.com.yandex_weather_app.model.clean.Weather;
import narek.example.com.yandex_weather_app.ui._common.base.MvpBasePresenter;
import narek.example.com.yandex_weather_app.ui.find_city.FindCityFragment;
import narek.example.com.yandex_weather_app.util.NetworkStatusChecker;

@InjectViewState
public class WeatherFragmentPresenter extends MvpBasePresenter<WeatherFragmentView> {

    private Repository repository;
    private DialogFragment suggestDialog;
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
                        loadWeather(cityEntity);
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
                                    getViewState().showError(R.string.data_not_updated);
                                }
                            })
            );

        } else {
            getViewState().hideSwipeRefresh();
            getViewState().showError(R.string.data_not_updated);

        }
    }

    private void sendWeatherData(@Nullable Weather weather) {
        if (weather != null) {
            getViewState().showWeather(weather);
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
                                }
                            })
            );
        } else {
            getViewState().hideSwipeRefresh();
            getViewState().showError(R.string.data_not_updated);

        }
    }
}
