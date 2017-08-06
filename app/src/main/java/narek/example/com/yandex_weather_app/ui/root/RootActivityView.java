package narek.example.com.yandex_weather_app.ui.root;

import android.support.annotation.StringRes;

import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

import narek.example.com.yandex_weather_app.ui._common.base.MvpBaseView;

@StateStrategyType(SkipStrategy.class)
public interface RootActivityView extends MvpBaseView {

    void setupToolbarAndDrawer();

    void openAboutUsFragment();



    void openSettingsFragment();

    void openWeatherFragment();

    void lockDrawer();

    void unlockDrawer();

    void changeToolbarIconToArrow();

    void changeToolbarIconToHamburger();

    void setToolBarTitle(@StringRes int titleId);

    void hideKeyBoard();
}
