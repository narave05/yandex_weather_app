package narek.example.com.yandex_weather_app.db;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.Relation;

import java.util.Date;
import java.util.List;

@Entity(tableName = "Weather")

public class WeatherEntity {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "city_id")
    private int cityId;

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

    public WeatherEntity(String cityName, Date date, float temperature, float pressure,
                         float humidity, float windSpeed, int conditionCode) {

        this.cityName = cityName;
        this.date = date;
        this.temperature = temperature;
        this.pressure = pressure;
        this.humidity = humidity;
        this.windSpeed = windSpeed;
        this.conditionCode = conditionCode;
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


    public static class WeatherEntityBuilder {

        private static final int NOT_MILLIS = 1000;
        private int id;
        private List<CityEntity> cityEntity;
        private String cityName;
        private Date date;
        private float temperature;
        private float pressure;
        private float humidity;
        private float windSpeed;
        private int conditionCode;

        public WeatherEntityBuilder() {
        }

        public WeatherEntity.WeatherEntityBuilder id(int id) {
            this.id = id;
            return this;
        }
        public WeatherEntity.WeatherEntityBuilder temperature(float temperature) {
            this.temperature = temperature;
            return this;
        }

        public WeatherEntity.WeatherEntityBuilder cityName(String cityName) {
            this.cityName = cityName;
            return this;
        }

        public WeatherEntity.WeatherEntityBuilder pressure(float pressure) {
            this.pressure = pressure;
            return this;
        }

        public WeatherEntity.WeatherEntityBuilder humidity(float humidity) {
            this.humidity = humidity;
            return this;
        }

        public WeatherEntity.WeatherEntityBuilder windSpeed(float windSpeed) {
            this.windSpeed = windSpeed;
            return this;
        }

        public WeatherEntity.WeatherEntityBuilder conditionCode(int conditionCode) {
            this.conditionCode = conditionCode;
            return this;
        }

        public WeatherEntity.WeatherEntityBuilder weatherUpdateDate(long millis) {
            this.date = new Date(millis * NOT_MILLIS);
            return this;
        }

        public WeatherEntity.WeatherEntityBuilder city(List<CityEntity> cityEntity) {
            this.cityEntity = cityEntity;
            return this;
        }


        public WeatherEntity createWeatherEntity() {
            return new WeatherEntity(id, cityEntity, cityName, date, temperature, pressure, humidity, windSpeed, conditionCode);
        }
    }
}
