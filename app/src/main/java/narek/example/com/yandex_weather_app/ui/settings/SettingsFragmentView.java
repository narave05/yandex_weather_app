package narek.example.com.yandex_weather_app.ui.settings;

import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

import narek.example.com.yandex_weather_app.ui._common.base.MvpBaseView;

@StateStrategyType(AddToEndSingleStrategy.class)
public interface SettingsFragmentView extends MvpBaseView {
    void setProgressText(int currentInterval);

    void updatedProgress(int currentInterval);
}
