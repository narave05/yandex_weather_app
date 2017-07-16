package narek.example.com.yandex_weather_app.ui.weather;

import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

import narek.example.com.yandex_weather_app.model.clean.Weather;
import narek.example.com.yandex_weather_app.ui.base.MvpBaseView;

@StateStrategyType(AddToEndSingleStrategy.class)
public interface WeatherFragmentView extends MvpBaseView {
    void showWeather(Weather weather);
}
