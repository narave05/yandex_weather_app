package narek.example.com.yandex_weather_app;


import org.junit.Before;
import org.junit.Test;

import narek.example.com.yandex_weather_app.util.UnitsConverter;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class UnitConverterTest {

    private UnitsConverter unitsConverter;

    @Before
    public void init(){
        unitsConverter = new UnitsConverter();
    }

    @Test
    public void convertTemperatureTest(){
        String result = unitsConverter.convertTemperature(275);
        assertThat(result, is("+1"));
    }

    @Test
    public void converTimeTest(){
        String timeResult = unitsConverter.convertTime(1502654580712L);
        assertThat(timeResult, is("11:03"));
    }
}
