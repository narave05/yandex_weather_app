package narek.example.com.yandex_weather_app.db;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

import java.util.Date;

import narek.example.com.yandex_weather_app.model.clean.Coords;

import static android.arch.persistence.room.ForeignKey.CASCADE;


@Entity(tableName = "weather", indices = {@Index(value = {"id", "city_name"}, unique = true)},
        foreignKeys = @ForeignKey(onDelete=CASCADE, entity = CityEntity.class, parentColumns = "city_id", childColumns = "id"))

public class WeatherEntity {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private double lat;

    private double lon;

    @ColumnInfo(name = "city_name")
    private String cityName;

    @Embedded
    private Date date;

    private float temperature;

    private float pressure;

    private float humidity;

    @ColumnInfo(name = "wind_speed")
    private float windSpeed;

    @ColumnInfo(name = "condition_code")
    private int conditionCode;

    public WeatherEntity() {
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public float getTemperature() {
        return temperature;
    }

    public void setTemperature(float temperature) {
        this.temperature = temperature;
    }

    public float getPressure() {
        return pressure;
    }

    public void setPressure(float pressure) {
        this.pressure = pressure;
    }

    public float getHumidity() {
        return humidity;
    }

    public void setHumidity(float humidity) {
        this.humidity = humidity;
    }

    public float getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(float windSpeed) {
        this.windSpeed = windSpeed;
    }

    public int getConditionCode() {
        return conditionCode;
    }

    public void setConditionCode(int conditionCode) {
        this.conditionCode = conditionCode;
    }
}
