package narek.example.com.yandex_weather_app.data.scheduler;

import android.content.Context;

import com.google.android.gms.gcm.GcmNetworkManager;
import com.google.android.gms.gcm.GcmTaskService;
import com.google.android.gms.gcm.PeriodicTask;
import com.google.android.gms.gcm.Task;
import com.google.android.gms.gcm.TaskParams;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import narek.example.com.yandex_weather_app.data.Repository;
import narek.example.com.yandex_weather_app.data.RepositoryImpl;
import narek.example.com.yandex_weather_app.data.locale.WeatherStorage;
import narek.example.com.yandex_weather_app.model.clean.Weather;

public class WeatherTask extends GcmTaskService {

    public static final long FLEX = 30L;
    private Repository repository;
    private Disposable disposable;

    public WeatherTask() {
    }

    @Inject
    public WeatherTask(Repository repository) {
        this.repository = repository;
    }

    @Override
    public int onRunTask(TaskParams taskParams) {
        disposable = repository.getWeatherData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Weather>() {
                    @Override
                    public void accept(@NonNull Weather weather) throws Exception {
                        WeatherStorage.save(weather);

                    }
                });
        return GcmNetworkManager.RESULT_SUCCESS;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (disposable != null) {
            disposable.dispose();
        }

    }

    public  void schedule(Context context, long updateInterval) {
        GcmNetworkManager gcmNetworkManager = GcmNetworkManager.getInstance(context);

        PeriodicTask periodicTask = new PeriodicTask.Builder()
                .setService(WeatherTask.class)
                .setUpdateCurrent(true)
                .setPeriod(updateInterval)
                .setFlex(FLEX)
                .setRequiredNetwork(Task.NETWORK_STATE_CONNECTED)
                .setRequiresCharging(false)
                .setPersisted(true)
                .setTag(ScheduleTag.WEATHER_TASK)
                .build();

        gcmNetworkManager.schedule(periodicTask);
    }
}
