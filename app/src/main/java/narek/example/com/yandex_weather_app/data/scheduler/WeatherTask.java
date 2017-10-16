package narek.example.com.yandex_weather_app.data.scheduler;

import android.content.Context;

import com.google.android.gms.gcm.GcmNetworkManager;
import com.google.android.gms.gcm.GcmTaskService;
import com.google.android.gms.gcm.PeriodicTask;
import com.google.android.gms.gcm.Task;
import com.google.android.gms.gcm.TaskParams;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import narek.example.com.yandex_weather_app.App;
import narek.example.com.yandex_weather_app.data.Repository;
import narek.example.com.yandex_weather_app.db.CityEntity;
import narek.example.com.yandex_weather_app.model.clean.Forecasts;
import narek.example.com.yandex_weather_app.model.clean.Weather;

public class WeatherTask extends GcmTaskService {

    public static final long FLEX_WEATHER = 30L;
    public static final int UPDATE_PERIOD = 86400;
    public static final int FLEX_FORECAST = 3600;
    private Disposable disposable;

    @Inject
    public Repository repository;
    private CityEntity cityEntity;

    public WeatherTask() {
        App.getInstance().getAppComponent().inject(this);
        getActiveCityFromDb();
    }

    @Override
    public int onRunTask(TaskParams taskParams) {

        switch (taskParams.getTag()) {
            case ScheduleTag.WEATHER_TASK:

                if (cityEntity != null) {
                    getWeather(cityEntity);
                }

                return GcmNetworkManager.RESULT_SUCCESS;
            case ScheduleTag.FORECAST_TASK:

                if (cityEntity != null) {
                    getForecast(cityEntity);
                }

                return GcmNetworkManager.RESULT_SUCCESS;
            default:
                return GcmNetworkManager.RESULT_FAILURE;
        }
    }

    private void getForecast(CityEntity activeCityFromDb) {
        disposable = repository.getForecastFromInternet(activeCityFromDb)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Forecasts>>() {
                    @Override
                    public void accept(@NonNull List<Forecasts> forecastsList) throws Exception {
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                    }
                });
    }

    void getActiveCityFromDb(){
        repository.getActiveCityFromDb()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<CityEntity>() {
                               @Override
                               public void accept(@NonNull CityEntity cityEntity) throws Exception {
                                   WeatherTask.this.cityEntity = cityEntity;
                               }
                           },
                        new Consumer<Throwable>() {
                            @Override
                            public void accept(@NonNull Throwable throwable) throws Exception {
                            }
                        });
    }

    private void getWeather(CityEntity cityEntity) {
        disposable = repository.getWeatherSingleFromInternet(cityEntity)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Weather>() {
                    @Override
                    public void accept(@NonNull Weather weather) throws Exception {
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                    }
                });
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        if (disposable != null) {
            disposable.dispose();
        }
    }

    public static void schedule(Context context, long updateInterval) {

        GcmNetworkManager gcmNetworkManager = GcmNetworkManager.getInstance(context);

        PeriodicTask weatherTask = new PeriodicTask.Builder()
                .setService(WeatherTask.class)
                .setUpdateCurrent(true)
                .setPeriod(updateInterval)
                .setFlex(FLEX_WEATHER)
                .setRequiredNetwork(Task.NETWORK_STATE_CONNECTED)
                .setRequiresCharging(false)
                .setPersisted(true)
                .setTag(ScheduleTag.WEATHER_TASK)
                .build();

        Task forecastTask = new PeriodicTask.Builder()
                .setService(WeatherTask.class)
                .setPeriod(UPDATE_PERIOD)
                .setFlex(FLEX_FORECAST)
                .setRequiredNetwork(Task.NETWORK_STATE_CONNECTED)
                .setTag(ScheduleTag.FORECAST_TASK)
                .setPersisted(true)
                .build();

        gcmNetworkManager.schedule(weatherTask);
        gcmNetworkManager.schedule(forecastTask);
    }
}
