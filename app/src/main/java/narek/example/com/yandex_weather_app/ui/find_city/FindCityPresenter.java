package narek.example.com.yandex_weather_app.ui.find_city;

/*
 * @author <a href="mailto: alyonamalchikhina@gmail.com">Alena Malchikhina</a>
 * @since 0.1
 */


import com.arellomobile.mvp.InjectViewState;

import java.util.List;
import java.util.StringTokenizer;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;
import narek.example.com.yandex_weather_app.R;
import narek.example.com.yandex_weather_app.data.Repository;
import narek.example.com.yandex_weather_app.data.RepositoryImpl;
import narek.example.com.yandex_weather_app.data.locale.WeatherStorage;
import narek.example.com.yandex_weather_app.model.clean.SuggestCity;
import narek.example.com.yandex_weather_app.model.clean.Weather;
import narek.example.com.yandex_weather_app.model.mapper.CityMapper;
import narek.example.com.yandex_weather_app.model.rest.PlacesResponse;
import narek.example.com.yandex_weather_app.ui._common.base.MvpBasePresenter;
import narek.example.com.yandex_weather_app.util.NetworkStatusChecker;

@InjectViewState
public class FindCityPresenter extends MvpBasePresenter<FindCityFragmentView> {

    private Repository repository = RepositoryImpl.getInstance();

    public void initKeyBoard() {
        getViewState().openKeyBoard();
    }

    public void editTextChanged(Observable<CharSequence> observable){
        observable.filter(new Predicate<CharSequence>() {
            @Override
            public boolean test(@NonNull CharSequence charSequence) throws Exception {
                return charSequence.length() > 0;
            }
        })
                .debounce(400, TimeUnit.MILLISECONDS, AndroidSchedulers.mainThread())
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
}
