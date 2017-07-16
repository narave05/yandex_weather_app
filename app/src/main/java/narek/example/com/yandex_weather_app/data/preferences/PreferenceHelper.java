package narek.example.com.yandex_weather_app.data.preferences;

import android.content.SharedPreferences;

import narek.example.com.yandex_weather_app.App;

/**
 * Created by Narek on 14.07.2017.
 */

public class PreferenceHelper {

    private static final Object lock = new Object();
    private static PreferenceHelper instance;
    private static final String INTERVAL_KEY = "INTERVAL_KEY";
    private SharedPreferences preferences = App.getInstance().getPreferences();
    private SharedPreferences.Editor editor = preferences.edit();

    public static PreferenceHelper getInstance() {
        if (instance == null) {
            synchronized (lock) {
                instance = new PreferenceHelper();
            }
        }
        return instance;
    }

    private PreferenceHelper() {
    }


    public void saveUpdateInterval(int hours) {
        editor.putInt(INTERVAL_KEY, hours)
                .apply();
    }

    public int getIntervalHoursInSeconds() {
        return preferences.getInt(INTERVAL_KEY, 1) * 3600;
    }

}
