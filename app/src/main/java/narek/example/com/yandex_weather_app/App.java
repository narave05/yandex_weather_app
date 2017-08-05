package narek.example.com.yandex_weather_app;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.facebook.stetho.Stetho;

import narek.example.com.yandex_weather_app.data.RepositoryImpl;
import narek.example.com.yandex_weather_app.data.preferences.PreferenceHelper;
import narek.example.com.yandex_weather_app.data.scheduler.WeatherTask;

import narek.example.com.yandex_weather_app.db.AppDatabase;
import narek.example.com.yandex_weather_app.di.AppComponent;

import narek.example.com.yandex_weather_app.di.AppModule;
import narek.example.com.yandex_weather_app.di.DaggerAppComponent;
import narek.example.com.yandex_weather_app.util.RxBus;

public class App extends Application {

    private static final String SHARED_PREF_NAME = "shared_pref_name";
    private static App instance;
    private SharedPreferences preferences;
    private static RxBus rxBus;
    private static AppComponent appComponent;
    private WeatherTask weatherTask;
    private static AppDatabase appDatabase;

    @Override
    public void onCreate() {
        super.onCreate();
        Stetho.initializeWithDefaults(this);
        instance = this;
        preferences = getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        rxBus = RxBus.instanceOf();
        appComponent = buildAppComponent();
        weatherTask = appComponent.provideWeatherTask();
        weatherTask.schedule(this, PreferenceHelper.getInstance().getIntervalHoursInSeconds());
        appDatabase = getAppComponent().provideDb();
    }

    public static AppDatabase getAppDatabase() {
        return appDatabase;
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
                .appModule(new AppModule(this))
                .build();
    }


}
