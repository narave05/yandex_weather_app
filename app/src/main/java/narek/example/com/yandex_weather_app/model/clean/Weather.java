package narek.example.com.yandex_weather_app.model.clean;

import android.support.annotation.NonNull;

import java.io.Serializable;
import java.util.Date;

import narek.example.com.yandex_weather_app.model.mapper.TimeMapper;

public class Weather implements Serializable {

    private static final float K = 273.15f;
    public static final int NOT_MILLIS = 1000;

    private TimeMapper timeMapper = new TimeMapper();
    private final City city;
    private final Date date;
    private final float temperature;
    private final float pressure;
    private final float humidity;
    private final float windSpeed;
    private final int conditionCode;


    private Weather(City city, float temperature, float pressure, float humidity, float windSpeed,
                    int conditionCode, Date date) {
        this.city = city;
        this.date = date;
        this.temperature = temperature;
        this.pressure = pressure;
        this.humidity = humidity;
        this.windSpeed = windSpeed;
        this.conditionCode = conditionCode;
    }

    @NonNull
    public City getCity() {
        return city;
    }

    @NonNull
    public String getDateString() {
        return timeMapper.transformToString(date);
    }

    public Date getDate() {
        return date;
    }

    public float getTemperature() {
        return temperature - K;
    }

    public float getPressure() {
        return pressure;
    }

    public float getHumidity() {
        return humidity;
    }

    public float getWindSpeed() {
        return windSpeed;
    }

    public int getConditionCode() {
        return conditionCode;
    }

    @Override
    public String toString() {
        return "Weather{" +
                "city=" + city +
                ", date=" + date +
                ", temperature=" + temperature +
                ", pressure=" + pressure +
                ", humidity=" + humidity +
                ", windSpeed=" + windSpeed +
                ", conditionCode=" + conditionCode +
                '}';
    }

    public static class WeatherBuilder {
        private City city;
        private Date date;
        private float temperature;
        private float pressure;
        private float humidity;
        private float windSpeed;
        private int conditionCode;

        public WeatherBuilder() {
        }

        public WeatherBuilder temperature(float temperature) {
            this.temperature = temperature;
            return this;
        }

        public WeatherBuilder pressure(float pressure) {
            this.pressure = pressure;
            return this;
        }

        public WeatherBuilder humidity(float humidity) {
            this.humidity = humidity;
            return this;
        }

        public WeatherBuilder windSpeed(float windSpeed) {
            this.windSpeed = windSpeed;
            return this;
        }

        public WeatherBuilder conditionCode(int conditionCode) {
            this.conditionCode = conditionCode;
            return this;
        }

        public WeatherBuilder weatherUpdateDate(long millis) {
            this.date = new Date(millis * NOT_MILLIS);
            return this;
        }

        public WeatherBuilder city(City city) {
            this.city = city;
            return this;
        }

        public Weather createWeather() {
            return new Weather(city, temperature, pressure, humidity, windSpeed, conditionCode, date);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Weather)) return false;

        Weather weather = (Weather) o;

        if (Float.compare(weather.temperature, temperature) != 0) return false;
        if (Float.compare(weather.pressure, pressure) != 0) return false;
        if (Float.compare(weather.humidity, humidity) != 0) return false;
        if (Float.compare(weather.windSpeed, windSpeed) != 0) return false;
        if (!city.equals(weather.city)) return false;
        return date != null ? date.equals(weather.date) : weather.date == null;

    }

    @Override
    public int hashCode() {
        int result = city.hashCode();
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (temperature != +0.0f ? Float.floatToIntBits(temperature) : 0);
        result = 31 * result + (pressure != +0.0f ? Float.floatToIntBits(pressure) : 0);
        result = 31 * result + (humidity != +0.0f ? Float.floatToIntBits(humidity) : 0);
        result = 31 * result + (windSpeed != +0.0f ? Float.floatToIntBits(windSpeed) : 0);
        return result;
    }
}
