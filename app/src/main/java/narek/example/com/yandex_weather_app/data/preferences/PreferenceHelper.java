package narek.example.com.yandex_weather_app.data.preferences;

import android.content.SharedPreferences;

import narek.example.com.yandex_weather_app.App;
import narek.example.com.yandex_weather_app.util.Const;

/**
 * Created by Narek on 14.07.2017.
 */

public class PreferenceHelper {

    private static final Object lock = new Object();
    private static final String CITY_LAT = "CITY_LAT";
    private static final String CITY_LON = "CITY_LON";
    private static final String MOSCOW_LON = String.valueOf(Const.moscowLon);
    private static final String MOSCOW_LAT = String.valueOf(Const.moscowLat);

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

    public void saveCityLat(String lat) {
        editor.putString(CITY_LAT, lat)
                .apply();
    }

    public String getCityLat() {
        return preferences.getString(CITY_LAT, MOSCOW_LAT);
    }
    public void saveCityLon(String lon) {
        editor.putString(CITY_LON, lon)
                .apply();
    }

    public String getCityLon() {
        return preferences.getString(CITY_LON, MOSCOW_LON);
    }
}
