package narek.example.com.yandex_weather_app.db;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

import narek.example.com.yandex_weather_app.model.clean.Coords;

@Entity(tableName = "city", indices = {@Index(value = {"lat", "lon", "city_id", "city_name"}, unique = true)})

public class CityEntity {

    @PrimaryKey (autoGenerate = true)
    @ColumnInfo(name = "city_id")
    private int cityId;

    @ColumnInfo(name = "city_name")
    private String cityName;

    private double lat;

    private double lon;

    @ColumnInfo(name = "is_active")
    private boolean isActive;

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }
}
