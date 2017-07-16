package narek.example.com.yandex_weather_app.ui.weather;

import com.arellomobile.mvp.InjectViewState;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import narek.example.com.yandex_weather_app.data.Repository;
import narek.example.com.yandex_weather_app.data.RepositoryImpl;
import narek.example.com.yandex_weather_app.data.locale.WeatherStorage;
import narek.example.com.yandex_weather_app.model.clean.Weather;
import narek.example.com.yandex_weather_app.ui.base.MvpBasePresenter;

@InjectViewState
public class WeatherFragmentPresenter extends MvpBasePresenter<WeatherFragmentView> {

    private Repository repository = RepositoryImpl.getInstance();

    WeatherFragmentPresenter() {
        showWeatherFromStorage();
        loadWeather();
    }

    public void loadWeather() {
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
                                getViewState().showWeather(weather);
                                getViewState().hideSwipeRefresh();
                            }
                        }, new Consumer<Throwable>() {
                            @Override
                            public void accept(@NonNull Throwable throwable) throws Exception {
                            }
                        })
        );
    }

    private void showWeatherFromStorage() {
        if (WeatherStorage.hasSavedWeather()) {
            Weather weather = WeatherStorage.read();
            getViewState().showWeather(weather);
        }
    }
}
