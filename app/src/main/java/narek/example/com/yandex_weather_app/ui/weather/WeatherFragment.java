package narek.example.com.yandex_weather_app.ui.weather;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.presenter.InjectPresenter;

import narek.example.com.yandex_weather_app.R;
import narek.example.com.yandex_weather_app.ui.base.MvpBaseFragment;

public class WeatherFragment extends MvpBaseFragment implements WeatherFragmentView {

    @InjectPresenter
    WeatherFragmentPresenter presenter;

    public static WeatherFragment newInstance() {
        return new WeatherFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_weather, container, false);
    }

}
