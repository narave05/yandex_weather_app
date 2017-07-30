package narek.example.com.yandex_weather_app.ui.settings;

import com.arellomobile.mvp.InjectViewState;

import javax.inject.Inject;

import narek.example.com.yandex_weather_app.data.Repository;
import narek.example.com.yandex_weather_app.data.RepositoryImpl;
import narek.example.com.yandex_weather_app.ui._common.base.MvpBasePresenter;

@InjectViewState
public class SettingsFragmentPresenter extends MvpBasePresenter<SettingsFragmentView> {

    Repository repository;

    @Inject
    public SettingsFragmentPresenter(Repository repository) {
        this.repository = repository;
    }

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
