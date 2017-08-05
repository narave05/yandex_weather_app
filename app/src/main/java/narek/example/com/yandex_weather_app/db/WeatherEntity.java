package narek.example.com.yandex_weather_app.db;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

import java.util.Date;


@Entity(tableName = "weather", indices = {@Index(value = {"weather_id"}, unique = true)},
        foreignKeys = @ForeignKey(entity = CityEntity.class, parentColumns = "city_id", childColumns = "id"))

public class WeatherEntity {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "city_id")
    private int cityId;

    @ColumnInfo(name = "city_name")
    private String cityName;

    @ColumnInfo(name = "weather_id")
    private String weatherId;

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

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public String getWeatherId() {
        return weatherId;
    }

    public void setWeatherId(String weatherId) {
        this.weatherId = weatherId;
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
