package narek.example.com.yandex_weather_app;

import com.google.gson.Gson;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Single;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import io.reactivex.observers.TestObserver;
import narek.example.com.yandex_weather_app.data.api.WeatherApi;
import narek.example.com.yandex_weather_app.model.clean.Forecasts;
import narek.example.com.yandex_weather_app.model.clean.Weather;
import narek.example.com.yandex_weather_app.model.mapper.ForecastMapper;
import narek.example.com.yandex_weather_app.model.mapper.WeatherMapper;
import narek.example.com.yandex_weather_app.model.rest.WeatherDataRes;
import narek.example.com.yandex_weather_app.model.rest.forecast.ForecastRes;
import narek.example.com.yandex_weather_app.model.rest.forecast.ListForecast;

import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class RepositoryForecastTest {
    @Mock
    WeatherApi weatherApi;

    private ForecastRes forecastRes;
    private List<Forecasts> forecastsList = new ArrayList<>();
    private final String jsonForecast = JsonProvider.getInstance().getForecastJson();

    @Before
    public void init(){
        Gson json = new Gson();
        forecastRes = json.fromJson(jsonForecast, ForecastRes.class);

        for (int i = 0; i < forecastRes.getList().size(); i++) {
            forecastsList.add(new Forecasts.ForecastsBuilder()
                    .clouds(forecastRes.getList().get(i).getClouds())
                    .dayTemp(forecastRes.getList().get(i).getTemp().getDay())
                    .degrees(forecastRes.getList().get(i).getDeg())
                    .humidity(forecastRes.getList().get(i).getHumidity())
                    .iconId(forecastRes.getList().get(i).getWeather().get(0).getId())
                    .nightTemp(forecastRes.getList().get(i).getTemp().getNight())
                    .pressure(forecastRes.getList().get(i).getPressure())
                    .rain(forecastRes.getList().get(i).getRain())
                    .speed(forecastRes.getList().get(i).getSpeed())
                    .time(forecastRes.getList().get(i).getDt())
                    .createForecasts());
        }

    }
    @Test
    public void isForecastResponseMappedWell(){

        when(weatherApi.getForecastFromApi(anyDouble(), anyDouble())).thenReturn(Single.just(forecastRes));
        TestObserver<List<Forecasts>> observer = weatherApi.getForecastFromApi(anyDouble(), anyDouble())
                .map(new Function<ForecastRes, List<Forecasts>>() {
                    @Override
                    public List<Forecasts> apply(@NonNull ForecastRes forecastRes) throws Exception {
                        List<Forecasts> list = new ArrayList<>();
                        for (ListForecast lf : forecastRes.getList()) {
                            list.add(new ForecastMapper().buildForecast(lf));
                        }
                        return list;
                    }
                }).test();
        observer.assertNoErrors().assertValue(forecastsList);
    }
}
