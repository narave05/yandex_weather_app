package narek.example.com.yandex_weather_app.data;

import io.reactivex.Single;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import narek.example.com.yandex_weather_app.data.api.WeatherApi;
import narek.example.com.yandex_weather_app.data.preferences.PreferenceHelper;
import narek.example.com.yandex_weather_app.model.clean.City;
import narek.example.com.yandex_weather_app.model.clean.Weather;
import narek.example.com.yandex_weather_app.model.rest.WeatherDataRes;

public class RepositoryImpl implements Repository {

    private WeatherApi api = new WeatherApi();
    private PreferenceHelper preferenceHelper = PreferenceHelper.getInstance();

    private static RepositoryImpl instance;

    public static RepositoryImpl getInstance() {
        if (instance == null) {
            synchronized (RepositoryImpl.class) {
                instance = new RepositoryImpl();
            }
        }
        return instance;
    }

    private RepositoryImpl() {
    }


    @Override
    public Single<Weather> getWeatherData() {
        return api.callWeatherDataByCityName("moscow,ru")
                .subscribeOn(Schedulers.io())
                .map(new Function<WeatherDataRes, Weather>() {
                    @Override
                    public Weather apply(@NonNull WeatherDataRes weatherDataRes) throws Exception {
                        return weatherResConvertToWeatherClean(weatherDataRes);
                    }
                });
    }

    @Override
    public int getCurrentUpdateInterval() {
        return preferenceHelper.getIntervalHoursInSeconds() / 3600;
    }

    @Override
    public void saveUpdateInterval(int currentInterval) {
        preferenceHelper.saveUpdateInterval(currentInterval);
    }

    @android.support.annotation.NonNull
    private Weather weatherResConvertToWeatherClean(@NonNull WeatherDataRes weatherDataRes) {
        City city = new City(weatherDataRes.name,
                weatherDataRes.coordRes.lon,
                weatherDataRes.coordRes.lat);
        return new Weather.WeatherBuilder()
                .city(city)
                .temperature(weatherDataRes.main.temp)
                .pressure(weatherDataRes.main.pressure)
                .humidity(weatherDataRes.main.humidity)
                .windSpeed(weatherDataRes.windRes.speed)
                .conditionCode(weatherDataRes.weatherRes.get(0).id)
                .weatherUpdateDate(weatherDataRes.dt)
                .createWeather();
    }
}
