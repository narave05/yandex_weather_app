package narek.example.com.yandex_weather_app.ui.settings;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.presenter.InjectPresenter;

import narek.example.com.yandex_weather_app.R;
import narek.example.com.yandex_weather_app.ui.base.MvpBaseFragment;

public class SettingsFragment extends MvpBaseFragment implements SettingsFragmentView {

    @InjectPresenter
    SettingsFragmentPresenter presenter;

    public static SettingsFragment newInstance() {
        return new SettingsFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_settings, container, false);
    }

}
