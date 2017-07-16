package narek.example.com.yandex_weather_app.model.clean;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Weather {

    private final City city;
    private final Date date;
    private float temperature;
    private float pressure;
    private float humidity;
    private float windSpeed;

    private String pattern = "EE HH:mm";
    private SimpleDateFormat format;

    public Weather(City city, float temperature, float pressure, float humidity, float windSpeed) {
        this.city = city;
        this.date = new Date();
        this.temperature = temperature;
        this.pressure = pressure;
        this.humidity = humidity;
        this.windSpeed = windSpeed;
        format = new SimpleDateFormat(pattern);
    }

    public City getCity() {
        return city;
    }

    public String getDateString() {
        return format.format(date);
    }

    public float getTemperature() {
        return temperature - 273.15f;
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
}
