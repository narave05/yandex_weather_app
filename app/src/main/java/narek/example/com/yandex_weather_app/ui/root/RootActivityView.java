package narek.example.com.yandex_weather_app.ui.root;

import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

import narek.example.com.yandex_weather_app.ui.base.MvpBaseView;

@StateStrategyType(AddToEndSingleStrategy.class)
public interface RootActivityView extends MvpBaseView {

    void setupToolbarAndDrawer();

    void openAboutUsFragment();

    void openSettingsFragment();

    void openWeatherFragment();
}
