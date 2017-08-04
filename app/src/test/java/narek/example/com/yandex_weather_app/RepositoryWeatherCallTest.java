package narek.example.com.yandex_weather_app;

import com.google.gson.Gson;

import org.junit.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import io.reactivex.Single;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import io.reactivex.observers.TestObserver;
import narek.example.com.yandex_weather_app.data.api.WeatherApi;
import narek.example.com.yandex_weather_app.model.clean.City;
import narek.example.com.yandex_weather_app.model.clean.Weather;
import narek.example.com.yandex_weather_app.model.mapper.WeatherMapper;
import narek.example.com.yandex_weather_app.model.rest.WeatherDataRes;

import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class RepositoryWeatherCallTest {

    @Mock
    WeatherApi weatherApi;

    private WeatherDataRes weatherDataRes;
    private final String jsonWeather = JsonProvider.getInstance().getWeatherJson();
    private Weather weather;

    @Before
    public void init(){
        Gson json = new Gson();
        weatherDataRes = json.fromJson(jsonWeather, WeatherDataRes.class);
        weather = new Weather.WeatherEntityBuilder()
                .city(new City(weatherDataRes.name, weatherDataRes.coordRes.lon, weatherDataRes.coordRes.lat))
                .temperature(weatherDataRes.main.temp)
                .pressure(weatherDataRes.main.pressure)
                .humidity(weatherDataRes.main.humidity)
                .windSpeed(weatherDataRes.windRes.speed)
                .conditionCode(weatherDataRes.weatherRes.get(0).id)
                .weatherUpdateDate(weatherDataRes.dt)
                .createWeather();
    }

    @Test
    public void isWeatherResponseMappedWell(){

        when(weatherApi.callWeatherDataByCityCoords(anyDouble(), anyDouble())).thenReturn(Single.just(weatherDataRes));
        TestObserver<Weather> observer = weatherApi.callWeatherDataByCityCoords(anyDouble(), anyDouble())
                .map(new Function<WeatherDataRes, Weather>() {
                    @Override
                    public Weather apply(@NonNull WeatherDataRes weatherDataRes) throws Exception {
                        return new WeatherMapper().transform(weatherDataRes);
                    }
                }).test();
        observer.assertNoErrors().assertValue(weather);
    }
}
