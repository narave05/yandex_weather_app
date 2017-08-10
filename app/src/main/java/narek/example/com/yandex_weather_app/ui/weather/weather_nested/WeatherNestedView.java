package narek.example.com.yandex_weather_app.ui.weather.weather_nested;


import android.support.annotation.NonNull;
import android.support.annotation.StringRes;

import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

import java.util.List;

import narek.example.com.yandex_weather_app.model.clean.Forecasts;
import narek.example.com.yandex_weather_app.model.clean.Weather;
import narek.example.com.yandex_weather_app.ui._common.base.MvpBaseView;

public interface WeatherNestedView extends MvpBaseView {

    void showForecast(@NonNull List<Forecasts> forecast);

    @StateStrategyType(OneExecutionStateStrategy.class)
    void showError(@StringRes int message);

}
