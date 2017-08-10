package narek.example.com.yandex_weather_app.ui.weather;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;

import butterknife.BindView;
import narek.example.com.yandex_weather_app.App;
import narek.example.com.yandex_weather_app.R;
import narek.example.com.yandex_weather_app.adapter.ViewPagerAdapter;
import narek.example.com.yandex_weather_app.model.clean.Weather;
import narek.example.com.yandex_weather_app.ui._common.base.MvpBaseFragment;
import narek.example.com.yandex_weather_app.ui._common.widget.WeatherImageView;


public class WeatherFragment extends MvpBaseFragment implements WeatherFragmentView, AppBarLayout.OnOffsetChangedListener, View.OnClickListener {

    private static final int PERCENTAGE_TO_ANIMATE_AVATAR = 20;
    public static final int PERCENT = 100;
    public static final int DURATION = 200;
    public static final int VALUE = 0;
    public static final int VALUE1 = 1;
    private boolean mIsAvatarShown = true;
    private int maxScrollSize;

    @InjectPresenter
    WeatherFragmentPresenter presenter;

    @ProvidePresenter
    public WeatherFragmentPresenter providePresenter() {
        return presenter = App.getInstance().getAppComponent().provideWeatherPresenter();
    }

    @BindView(R.id.pager)
    ViewPager viewPager;

    @BindView(R.id.tab_layout)
    TabLayout tabLayout;

    @BindView(R.id.city_name_tv)
    TextView cityName;

    @BindView(R.id.weatherImageView)
    WeatherImageView weatherImg;

    @BindView(R.id.temperature_tv)
    TextView temperature;

    @BindView(R.id.update_time_tv)
    TextView weatherUpdateDate;

    @BindView(R.id.umbrella_iv)
    ImageView umbrellaIv;

    @BindView(R.id.humidity_tv)TextView humidityTv;

    @BindView(R.id.wind_speed_tv)TextView windTv;

    @BindView(R.id.app_bar_weather)AppBarLayout appBarLayout;

    @BindView(R.id.alien_iv)ImageButton alienIb;


    public static WeatherFragment newInstance() {
        return new WeatherFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_weather, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter.setupViewPager(getChildFragmentManager());
        tabLayout.setupWithViewPager(viewPager, true);
        appBarLayout.addOnOffsetChangedListener(this);
        maxScrollSize = appBarLayout.getTotalScrollRange();
        alienIb.setOnClickListener(this);
    }

    @Override
    public void setupViewPager(ViewPagerAdapter adapter) {
        viewPager.setAdapter(adapter);
    }

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
        if (maxScrollSize == VALUE)
            maxScrollSize = appBarLayout.getTotalScrollRange();

        int percentage = (Math.abs(verticalOffset)) * PERCENT / maxScrollSize;

        if (percentage >= PERCENTAGE_TO_ANIMATE_AVATAR && mIsAvatarShown) {
            mIsAvatarShown = false;

            alienIb.animate()
                    .scaleY(VALUE).scaleX(VALUE)
                    .setDuration(DURATION)
                    .start();
        }

        if (percentage <= PERCENTAGE_TO_ANIMATE_AVATAR && !mIsAvatarShown) {
            mIsAvatarShown = true;

            alienIb.animate()
                    .scaleY(VALUE1).scaleX(VALUE1)
                    .start();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.alien_iv:
                presenter.loadWeatherFromInternet();
                break;
        }
    }


    @Override
    public void showWeather(@NonNull Weather weather) {

        cityName.setText(weather.getCity().getName().toUpperCase());
        weatherImg.setWeatherImage(weather.getConditionCode());
        temperature.setText(String.valueOf((int) weather.getTemperature()));
        humidityTv.setText(String.valueOf((int) weather.getHumidity()) + " %");
        windTv.setText(String.valueOf((int) weather.getWindSpeed()) + " " + getString(R.string.m_s));
        weatherUpdateDate.setText(weather.getDateString().toUpperCase());
    }
    @Override
    public void showError(@StringRes int message) {
        if (getActivity() != null) {
            Toast.makeText(getActivity(), getString(message), Toast.LENGTH_SHORT).show();
        }
    }
}
