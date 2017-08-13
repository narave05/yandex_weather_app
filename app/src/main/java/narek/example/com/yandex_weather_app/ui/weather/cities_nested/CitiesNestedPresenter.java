package narek.example.com.yandex_weather_app.ui.weather.cities_nested;


import android.support.v4.app.DialogFragment;

import com.arellomobile.mvp.InjectViewState;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import narek.example.com.yandex_weather_app.data.Repository;
import narek.example.com.yandex_weather_app.db.CityEntity;
import narek.example.com.yandex_weather_app.model.clean.City;
import narek.example.com.yandex_weather_app.ui._common.base.MvpBasePresenter;
import narek.example.com.yandex_weather_app.ui.find_city.FindCityFragment;

@InjectViewState
public class CitiesNestedPresenter extends MvpBasePresenter<CitiesNestedView> {
    private Repository repository;
    private DialogFragment suggestDialog;
    private CityEntity currentCityEntity;
    private List<CityEntity> listCities;


    @Inject
    public CitiesNestedPresenter(Repository repository) {
        this.repository = repository;
        getAllCities();
        getActiveCity();
    }

    private void getActiveCity() {
        compositeDisposable.add(repository.getCityFromDb()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<CityEntity>() {
                    @Override
                    public void accept(@NonNull CityEntity cityEntity) throws Exception {
                        currentCityEntity = cityEntity;
                    }
                }));
    }

    private void getAllCities() {
        compositeDisposable.add(
                repository.getAllCitiesFlowable()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<CityEntity>>() {
                    @Override
                    public void accept(@NonNull List<CityEntity> cityList) throws Exception {

                        listCities = cityList;
                        getViewState().initAdapter(cityList);

                        if (suggestDialog != null) {
                            getViewState().dismissDialog(suggestDialog);
                        }
                    }
                })
        );
    }

    public void updateActiveCity(CityEntity item) {
        repository.updateActiveCity(item);
    }

    public void fabClicked() {
        suggestDialog = FindCityFragment.newInstance();
        getViewState().showDialogCitySuggest(suggestDialog);
    }

    public void deleteCity(int adapterPosition) {
        repository.deleteCity(listCities.get(adapterPosition));
    }
}
