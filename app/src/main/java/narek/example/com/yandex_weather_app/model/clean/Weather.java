package narek.example.com.yandex_weather_app.model.clean;

import java.util.Date;

public class Weather {

    private final City city;
    private final Date date;
    private float temperature;
    private float pressure;
    private float humidity;

    public Weather(City city, float temperature, float pressure, float humidity) {
        this.city = city;
        this.date = new Date();
        this.temperature = temperature;
        this.pressure = pressure;
        this.humidity = humidity;
    }

    public City getCity() {
        return city;
    }

    public Date getDate() {
        return date;
    }

    public float getTemperature() {
        return temperature;
    }

    public float getPressure() {
        return pressure;
    }

    public float getHumidity() {
        return humidity;
    }
}
