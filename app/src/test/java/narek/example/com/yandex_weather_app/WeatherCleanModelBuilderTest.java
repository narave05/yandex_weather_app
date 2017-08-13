package narek.example.com.yandex_weather_app;


import org.junit.Test;

import java.util.Date;

import narek.example.com.yandex_weather_app.model.clean.City;
import narek.example.com.yandex_weather_app.model.clean.Weather;

import static junit.framework.Assert.assertEquals;
import static org.hamcrest.MatcherAssert.assertThat;


public class WeatherCleanModelBuilderTest {

    @Test
    public void createWeatherBuilderTest(){

        Weather weather = new Weather.WeatherEntityBuilder()
                .city(null)
                .weatherUpdateDate(0L)
                .conditionCode(1)
                .humidity(100f)
                .pressure(100f)
                .temperature(12.4f)
                .windSpeed(12.4f)
                .createWeather();

        assertEquals(weather.getCity(), null);
        assertEquals(weather.getConditionCode(), 1);
        assertEquals(weather.getDateString(), "Thu 03:00");
        assertEquals(weather.getHumidity(), 100f);
        assertEquals(weather.getPressure(), 100f);
        assertEquals(weather.getTemperature(), 12.4f);
        assertEquals(weather.getWindSpeed(), 12.4f);
    }
}
