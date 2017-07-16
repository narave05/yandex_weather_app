package narek.example.com.yandex_weather_app.ui.root;

import com.arellomobile.mvp.InjectViewState;

import narek.example.com.yandex_weather_app.ui.base.MvpBasePresenter;

@InjectViewState
public class RootActivityPresenter extends MvpBasePresenter<RootActivityView> {

    public void init() {
        getViewState().setupToolbarAndDrawer();
        getViewState().openWeatherFragment();
    }

    void onHomeItemClick() {
        getViewState().openWeatherFragment();
    }

    void onSettingsItemClick() {
        getViewState().openSettingsFragment();
    }

    void onAboutUsItemClick() {
        getViewState().openAboutUsFragment();
    }

}
