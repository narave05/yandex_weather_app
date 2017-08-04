package narek.example.com.yandex_weather_app.ui.find_city;


import com.arellomobile.mvp.InjectViewState;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;
import narek.example.com.yandex_weather_app.R;
import narek.example.com.yandex_weather_app.data.Repository;
import narek.example.com.yandex_weather_app.model.clean.Coords;
import narek.example.com.yandex_weather_app.model.clean.CoordsModel;
import narek.example.com.yandex_weather_app.model.clean.SuggestCity;
import narek.example.com.yandex_weather_app.ui._common.base.MvpBasePresenter;
import narek.example.com.yandex_weather_app.util.NetworkStatusChecker;

@InjectViewState
public class FindCityPresenter extends MvpBasePresenter<FindCityFragmentView> {

    private Repository repository;
    private final int timeout = 400;

    @Inject
    public FindCityPresenter(Repository repository) {
        this.repository = repository;
    }

    public void initKeyBoard() {
        getViewState().openKeyBoard();
    }

    void editTextChanged(Observable<CharSequence> observable){

        observable.filter(new Predicate<CharSequence>() {
            @Override
            public boolean test(@NonNull CharSequence charSequence) throws Exception {
                return charSequence.length() > 0;
            }
        })
                .debounce(timeout, TimeUnit.MILLISECONDS, AndroidSchedulers.mainThread())
                .map(new Function<CharSequence, String>() {
                    @Override
                    public String apply(@NonNull CharSequence charSequence) throws Exception {
                        citiesApiCall(charSequence.toString());
                        return charSequence.toString();
                    }
                })
                .distinctUntilChanged()
                .subscribe();
    }

    private void citiesApiCall(final String s) {
        if (NetworkStatusChecker.isNetworkAvailable()) {
            compositeDisposable.add(
                    repository.getPlacesSuggestion(s)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())

                            .subscribe(new Consumer<List<SuggestCity>>() {
                                @Override
                                public void accept(@NonNull List<SuggestCity> suggestCities) throws Exception {
                                    getViewState().showCitiesList(suggestCities);
                                }
                            }, new Consumer<Throwable>() {
                                @Override
                                public void accept(@NonNull Throwable throwable) throws Exception {
                                    getViewState().showError(R.string.data_not_updated);
                                }
                            })
            );
        } else {
            getViewState().showError(R.string.data_not_updated);

        }
    }
    void callForCoords(final String cityId){
        if (NetworkStatusChecker.isNetworkAvailable()) {
            compositeDisposable.add(
                    repository.callForCityCoords(cityId)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())

                            .subscribe(new Consumer<Coords>() {
                                @Override
                                public void accept(@NonNull Coords coords) throws Exception {
                                    repository.saveCityCoords(coords.getLat(), coords.getLon());
                                    new CoordsModel().setCoords(coords);
                                }
                            }, new Consumer<Throwable>() {
                                @Override
                                public void accept(@NonNull Throwable throwable) throws Exception {
                                    getViewState().showError(R.string.data_not_updated);
                                }
                            })
            );
        } else {
            getViewState().showError(R.string.data_not_updated);

        }
    }

}
