package narek.example.com.yandex_weather_app.ui.weather;

import com.arellomobile.mvp.InjectViewState;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.annotations.Nullable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import narek.example.com.yandex_weather_app.R;
import narek.example.com.yandex_weather_app.data.Repository;
import narek.example.com.yandex_weather_app.data.RepositoryImpl;
import narek.example.com.yandex_weather_app.data.locale.WeatherStorage;
import narek.example.com.yandex_weather_app.model.clean.Weather;
import narek.example.com.yandex_weather_app.ui._common.base.MvpBasePresenter;
import narek.example.com.yandex_weather_app.util.NetworkStatusChecker;

@InjectViewState
public class WeatherFragmentPresenter extends MvpBasePresenter<WeatherFragmentView> {

    private Repository repository = RepositoryImpl.getInstance();

    WeatherFragmentPresenter() {
        showWeatherFromStorage();
        loadWeather();
    }

    public void loadWeather() {
        if (NetworkStatusChecker.isNetworkAvailable()) {
            compositeDisposable.add(
                    repository.getWeatherData()
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .doAfterSuccess(new Consumer<Weather>() {
                                @Override
                                public void accept(@NonNull Weather weather) throws Exception {
                                    WeatherStorage.save(weather);
                                }
                            })
                            .subscribe(new Consumer<Weather>() {
                                @Override
                                public void accept(@NonNull Weather weather) throws Exception {
                                    sendWeatherData(weather);
                                    getViewState().hideSwipeRefresh();
                                }
                            }, new Consumer<Throwable>() {
                                @Override
                                public void accept(@NonNull Throwable throwable) throws Exception {
                                    getViewState().hideSwipeRefresh();
                                    getViewState().showError(R.string.data_not_updated);
                                }
                            })
            );
        } else {
            getViewState().hideSwipeRefresh();
            getViewState().showError(R.string.data_not_updated);

        }
    }

    private void showWeatherFromStorage() {
        if (WeatherStorage.hasSavedWeather()) {
            Weather weather = WeatherStorage.read();
            sendWeatherData(weather);
        }
    }

    private void sendWeatherData(@Nullable Weather weather) {
        if (weather != null) {
            getViewState().showWeather(weather);
        }
    }
}
