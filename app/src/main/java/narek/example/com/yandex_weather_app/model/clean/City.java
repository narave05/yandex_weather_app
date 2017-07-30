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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof City)) return false;

        City city = (City) o;

        if (Float.compare(city.lon, lon) != 0) return false;
        if (Float.compare(city.lat, lat) != 0) return false;
        return name.equals(city.name);

    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + (lon != +0.0f ? Float.floatToIntBits(lon) : 0);
        result = 31 * result + (lat != +0.0f ? Float.floatToIntBits(lat) : 0);
        return result;
    }
}
