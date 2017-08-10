package narek.example.com.yandex_weather_app.ui.weather.cities_nested;


import com.arellomobile.mvp.InjectViewState;

import javax.inject.Inject;

import narek.example.com.yandex_weather_app.data.Repository;
import narek.example.com.yandex_weather_app.ui._common.base.MvpBasePresenter;
@InjectViewState
public class CitiesNestedPresenter extends MvpBasePresenter<CitiesNestedView> {
    private Repository repository;


    @Inject
    public CitiesNestedPresenter(Repository repository) {
        this.repository = repository;
    }
}
