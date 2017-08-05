package narek.example.com.yandex_weather_app.ui.root;

import com.arellomobile.mvp.InjectViewState;
import com.facebook.stetho.inspector.elements.ShadowDocument;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import narek.example.com.yandex_weather_app.App;
import narek.example.com.yandex_weather_app.R;
import narek.example.com.yandex_weather_app.data.Repository;
import narek.example.com.yandex_weather_app.db.CityEntity;
import narek.example.com.yandex_weather_app.model.clean.Coords;
import narek.example.com.yandex_weather_app.ui._common.base.MvpBasePresenter;
import narek.example.com.yandex_weather_app.util.FragmentTag;

@InjectViewState
public class RootActivityPresenter extends MvpBasePresenter<RootActivityView> {

    private Repository repository;
    private FragmentTag currentFragmentTag = FragmentTag.WEATHER;

    @Inject
    public RootActivityPresenter(Repository repository) {
        this.repository = repository;
    }

    void init() {
        subscribeToNewCityFromDb();
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

    private void subscribeToNewCityFromDb() {
        compositeDisposable.add(repository.getCityFromDb()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<CityEntity>() {
                    @Override
                    public void accept(@NonNull CityEntity cityEntity) throws Exception {
                        onBackPressed();
                    }
                }));
    }

    public void onHomeItemClick() {
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

    public void onSettingsItemClick() {
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

    public void onAboutUsItemClick() {
        navigateToAboutAs();
    }

    private void navigateToAboutAs() {
        currentFragmentTag = FragmentTag.ABOUT;
        lockDrawerAndChangeIcon();
        getViewState().openAboutUsFragment();
        getViewState().setToolBarTitle(R.string.abut_us_title);
    }

    public void onBackPressed() {
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
