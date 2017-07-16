package narek.example.com.yandex_weather_app.ui.weather;

import android.util.Log;

import com.arellomobile.mvp.InjectViewState;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import narek.example.com.yandex_weather_app.data.Repository;
import narek.example.com.yandex_weather_app.data.RepositoryImpl;
import narek.example.com.yandex_weather_app.model.clean.Weather;
import narek.example.com.yandex_weather_app.ui.base.MvpBasePresenter;

@InjectViewState
public class WeatherFragmentPresenter extends MvpBasePresenter<WeatherFragmentView> {

    private Repository repository = new RepositoryImpl();

    WeatherFragmentPresenter(){
        Log.e("WeatherPresenter", "WeatherFragmentPresenter: ");
        init();
    }

    void init() {
        compositeDisposable.add(
                repository.getWeatherData()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<Weather>() {
                            @Override
                            public void accept(@NonNull Weather weather) throws Exception {
                                Log.e("getWeatherData", "apply: ");
                                getViewState().showWeather(weather);
                            }
                        }, new Consumer<Throwable>() {
                            @Override
                            public void accept(@NonNull Throwable throwable) throws Exception {
                                Log.e("getWeatherData", "accept: " + throwable);
                            }
                        })
        );
    }
}
