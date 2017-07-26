package narek.example.com.yandex_weather_app;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import narek.example.com.yandex_weather_app.data.preferences.PreferenceHelper;
import narek.example.com.yandex_weather_app.data.scheduler.WeatherTask;
import narek.example.com.yandex_weather_app.util.RxBus;

public class App extends Application {

    private static final String SHARED_PREF_NAME = "shared_pref_name";
    private static App instance;
    private SharedPreferences preferences;
    private static RxBus rxBus;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        preferences = getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        WeatherTask.schedule(this, PreferenceHelper.getInstance().getIntervalHoursInSeconds());
        rxBus = RxBus.instanceOf();
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
}
