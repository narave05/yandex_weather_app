package narek.example.com.yandex_weather_app.ui.root;

import android.util.Log;

import com.arellomobile.mvp.InjectViewState;

import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import narek.example.com.yandex_weather_app.App;
import narek.example.com.yandex_weather_app.R;
import narek.example.com.yandex_weather_app.model.clean.Coords;
import narek.example.com.yandex_weather_app.ui._common.base.MvpBasePresenter;
import narek.example.com.yandex_weather_app.util.FragmentTag;

import static android.content.ContentValues.TAG;

@InjectViewState
public class RootActivityPresenter extends MvpBasePresenter<RootActivityView> {

    private FragmentTag currentFragmentTag = FragmentTag.WEATHER;

    void init() {
        subscribeToRxBus();
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
            case FIND:
                navigateToFindCityFragment();
                break;
        }

    }

    private void subscribeToRxBus() {
        App.getRxBus().getEvents().subscribe(new Consumer<Object>() {
            @Override
            public void accept(@NonNull Object o) throws Exception {
                if (o instanceof Coords) {
                    onBackPressed();
                }
            }
        });
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
        getViewState().hideKeyBoard();
    }

    public void onFindCityItemClick() {
        navigateToFindCityFragment();
    }

    private void navigateToFindCityFragment() {
        currentFragmentTag = FragmentTag.FIND;
        lockDrawerAndChangeIcon();
        getViewState().openFindCityFragment();
        getViewState().setToolBarTitle(R.string.find_city);
    }
}
