package narek.example.com.yandex_weather_app.ui.settings;

import com.arellomobile.mvp.InjectViewState;

import narek.example.com.yandex_weather_app.data.Repository;
import narek.example.com.yandex_weather_app.data.RepositoryImpl;
import narek.example.com.yandex_weather_app.ui._common.base.MvpBasePresenter;

@InjectViewState
public class SettingsFragmentPresenter extends MvpBasePresenter<SettingsFragmentView> {

    Repository repository = RepositoryImpl.getInstance();

    void init() {
        int currentUpdateInterval = repository.getCurrentUpdateInterval();
        getViewState().setProgressText(currentUpdateInterval);
        getViewState().updatedProgress(currentUpdateInterval - 1);
    }

    void onCurrentIntervalChanged(int progress) {
        int currentUpdateInterval = progress + 1;
        repository.saveUpdateInterval(currentUpdateInterval);
    }

    void onCurrentProgressChanged(int progress) {
        getViewState().setProgressText(progress + 1);
    }
}
