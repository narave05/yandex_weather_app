package narek.example.com.yandex_weather_app.db;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

import narek.example.com.yandex_weather_app.model.clean.Coords;

@Entity(tableName = "City", indices = {@Index(value = {"city_name"}, unique = true)})

public class CityEntity {

    @PrimaryKey
    @ColumnInfo(name = "city_id")
    private int cityId;

    @ColumnInfo(name = "city_name")
    private String cityName;

    @Embedded
    private Coords coords;

    @ColumnInfo(name = "is_active")
    private boolean isActive;


    public CityEntity(String cityName, Coords coords, boolean isActive) {
        this.cityName = cityName;
        this.coords = coords;
        this.isActive = isActive;
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

    public static class CityEntityBuilder{

        private int cityId;
        private String cityName;
        private Coords coords;
        private boolean isActive;

        public CityEntityBuilder cityEntityName(String cityEntityName){
            cityName = cityEntityName;
            return this;
        }

        public CityEntityBuilder cityEntityCoords(Coords coords) {
            this.coords = coords;
            return this;
        }

        public CityEntityBuilder isActive(boolean isActive) {
            this.isActive = isActive;
            return this;
        }

        public CityEntity createCityEntity() {
            return new CityEntity(cityName, coords, isActive);
        }
    }
}
