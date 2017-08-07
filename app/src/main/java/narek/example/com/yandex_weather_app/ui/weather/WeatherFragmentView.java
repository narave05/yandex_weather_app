package narek.example.com.yandex_weather_app.ui.weather;

import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;

import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

import narek.example.com.yandex_weather_app.model.clean.Weather;
import narek.example.com.yandex_weather_app.ui._common.base.MvpBaseView;

@StateStrategyType(AddToEndSingleStrategy.class)
public interface WeatherFragmentView extends MvpBaseView {

    void showWeather(@NonNull Weather weather);

    @StateStrategyType(OneExecutionStateStrategy.class)
    void showError(@StringRes int message);

    void hideSwipeRefresh();

    void showDialogCitySuggest(DialogFragment dialogFragment);

    void dismissDialog(DialogFragment dialogFragment);
}
