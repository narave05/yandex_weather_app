package narek.example.com.yandex_weather_app.ui.root;

import android.util.Log;

import com.arellomobile.mvp.InjectViewState;

import narek.example.com.yandex_weather_app.R;
import narek.example.com.yandex_weather_app.ui._common.base.MvpBasePresenter;
import narek.example.com.yandex_weather_app.util.FragmentTag;

import static android.content.ContentValues.TAG;

@InjectViewState
public class RootActivityPresenter extends MvpBasePresenter<RootActivityView> {

    private FragmentTag currentFragmentTag = FragmentTag.WEATHER;

    void init() {
        getViewState().setupToolbarAndDrawer();
        switch (currentFragmentTag) {
            case WEATHER:
                navigateToHome();
                break;
            case SETTINGS:
                navigateToSettings();
                break;
            case ABOUT:
                navigateToAboutAs();
                break;
        }

    }

    void onHomeItemClick() {
        navigateToHome();
    }

    private void navigateToHome() {
        currentFragmentTag = FragmentTag.WEATHER;
        unlockDrawerAndChengeIcon();
        getViewState().openWeatherFragment();
        getViewState().setToolBarTitle(R.string.weather_title);
    }

    private void unlockDrawerAndChengeIcon() {
        getViewState().changeToolbarIconToHamburger();
        getViewState().unlockDrawer();
    }

    void onSettingsItemClick() {
        navigateToSettings();
    }

    private void navigateToSettings() {
        currentFragmentTag = FragmentTag.SETTINGS;
        lockDrawerAndChangeIcon();
        getViewState().openSettingsFragment();
        getViewState().setToolBarTitle(R.string.settings_title);
    }

    private void lockDrawerAndChangeIcon() {
        getViewState().lockDrawer();
        getViewState().changeToolbarIconToArrow();
    }

    void onAboutUsItemClick() {
        navigateToAboutAs();
    }

    private void navigateToAboutAs() {
        currentFragmentTag = FragmentTag.ABOUT;
        lockDrawerAndChangeIcon();
        getViewState().openAboutUsFragment();
        getViewState().setToolBarTitle(R.string.abut_us_title);
    }

    void onBackPressed() {
        navigateToHome();
        unlockDrawerAndChengeIcon();
        getViewState().setToolBarTitle(R.string.weather_title);
    }

    public void onFindSityItemClick() {
        navigateToFindCityFragment();
    }

    private void navigateToFindCityFragment() {
        currentFragmentTag = FragmentTag.FIND;
        lockDrawerAndChangeIcon();
        getViewState().openFindCityFragment();
        getViewState().setToolBarTitle(R.string.find_city);
    }
}
