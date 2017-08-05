package narek.example.com.yandex_weather_app.db;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

import narek.example.com.yandex_weather_app.model.clean.Coords;

@Entity(tableName = "city", indices = {@Index(value = {"city_name", "city_place_id"}, unique = true)})

public class CityEntity {

    @PrimaryKey
    @ColumnInfo(name = "city_id")
    private int cityId;

    @ColumnInfo(name = "city_name")
    private String cityName;

    @ColumnInfo(name = "city_place_id")
    private String cityPlaceId;

    @Embedded
    private Coords coords;

    @ColumnInfo(name = "is_active")
    private boolean isActive;

    public String getCityPlaceId() {
        return cityPlaceId;
    }

    public void setCityPlaceId(String cityPlaceId) {
        this.cityPlaceId = cityPlaceId;
    }

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

    public Coords getCoords() {
        return coords;
    }

    public void setCoords(Coords coords) {
        this.coords = coords;
    }
}
