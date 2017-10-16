package narek.example.com.yandex_weather_app.di;


import javax.inject.Singleton;

import dagger.Component;
import narek.example.com.yandex_weather_app.App;
import narek.example.com.yandex_weather_app.data.scheduler.WeatherTask;
import narek.example.com.yandex_weather_app.db.AppDatabase;
import narek.example.com.yandex_weather_app.ui.find_city.FindCityPresenter;
import narek.example.com.yandex_weather_app.ui.root.RootActivityPresenter;
import narek.example.com.yandex_weather_app.ui.settings.SettingsFragmentPresenter;
import narek.example.com.yandex_weather_app.ui.weather.WeatherFragmentPresenter;
import narek.example.com.yandex_weather_app.ui.weather.cities_nested.CitiesNestedPresenter;

@Component(modules = {AppModule.class})
@Singleton
public interface AppComponent {

    WeatherFragmentPresenter provideWeatherPresenter();
    CitiesNestedPresenter provideCitiesNestedPresenter();
    FindCityPresenter provideFindCityPresenter();
    RootActivityPresenter provideRootActivityPresenter();
    SettingsFragmentPresenter provideSettingsPresenter();
    AppDatabase provideDb();

    void inject(WeatherTask weatherTask);
}
