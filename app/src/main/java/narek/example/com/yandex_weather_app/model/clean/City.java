package narek.example.com.yandex_weather_app.model.clean;

import android.support.annotation.NonNull;

import java.io.Serializable;

import narek.example.com.yandex_weather_app.util.Const;

public class City implements Serializable{
    private final String name;
    private final float lon;
    private final float lat;

    public City(String name, float lon, float lat) {
        this.name = name == null ? Const.EMPTY_STRING : name;
        this.lon = lon;
        this.lat = lat;
    }


    @NonNull
    public String getName() {
        return name;
    }

    public float getLongitude() {
        return lon;
    }

    public float getLatitude() {
        return lat;
    }
}
