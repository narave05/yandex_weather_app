package narek.example.com.yandex_weather_app;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import narek.example.com.yandex_weather_app.data.RepositoryImpl;
import narek.example.com.yandex_weather_app.data.preferences.PreferenceHelper;
import narek.example.com.yandex_weather_app.data.scheduler.WeatherTask;

import narek.example.com.yandex_weather_app.di.AppComponent;
import narek.example.com.yandex_weather_app.di.DaggerAppComponent;
import narek.example.com.yandex_weather_app.util.RxBus;

public class App extends Application {

    private static final String SHARED_PREF_NAME = "shared_pref_name";
    private static App instance;
    private SharedPreferences preferences;
    private static RxBus rxBus;
    private static AppComponent appComponent;
    private WeatherTask weatherTask;




    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        preferences = getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        rxBus = RxBus.instanceOf();
        appComponent = buildAppComponent();
        weatherTask = appComponent.provideWeatherTask();
        weatherTask.schedule(this, PreferenceHelper.getInstance().getIntervalHoursInSeconds());
    }


    public static RxBus getRxBus(){
        return rxBus;
    }

    public static App getInstance() {
        return instance;
    }

    public SharedPreferences getPreferences() {
        return preferences;
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }


    private AppComponent buildAppComponent() {
        return DaggerAppComponent.builder()
                .build();
    }

}
