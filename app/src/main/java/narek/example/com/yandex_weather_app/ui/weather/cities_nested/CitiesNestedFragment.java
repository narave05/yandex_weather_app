package narek.example.com.yandex_weather_app.ui.weather.cities_nested;


import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;

import narek.example.com.yandex_weather_app.App;
import narek.example.com.yandex_weather_app.R;
import narek.example.com.yandex_weather_app.ui._common.base.MvpBaseFragment;
import narek.example.com.yandex_weather_app.ui._common.base.MvpBasePresenter;
import narek.example.com.yandex_weather_app.ui.weather.weather_nested.WeatherNestedFragment;

public class CitiesNestedFragment extends MvpBaseFragment implements CitiesNestedView {

    @InjectPresenter
    CitiesNestedPresenter presenter;

    @ProvidePresenter
    public CitiesNestedPresenter providePresenter(){
        return App.getInstance().getAppComponent().provideCitiesNestedPresenter();
    }

    public CitiesNestedFragment() {
        // Required empty public constructor
    }
    public static CitiesNestedFragment newInstance() {
        return new CitiesNestedFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cities_nested, container, false);
    }

    @Override
    public void showDialogCitySuggest(DialogFragment dialogFragment) {
        dialogFragment.show(getChildFragmentManager(), null);
    }

    @Override
    public void dismissDialog(DialogFragment dialogFragment) {
        dialogFragment.dismiss();
    }

}
