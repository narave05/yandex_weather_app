package narek.example.com.yandex_weather_app.ui.weather;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.arellomobile.mvp.presenter.InjectPresenter;

import butterknife.BindView;
import narek.example.com.yandex_weather_app.R;
import narek.example.com.yandex_weather_app.model.clean.Weather;
import narek.example.com.yandex_weather_app.ui.base.MvpBaseFragment;
import narek.example.com.yandex_weather_app.ui.widget.WeatherImageView;


public class WeatherFragment extends MvpBaseFragment implements WeatherFragmentView,
        SwipeRefreshLayout.OnRefreshListener {

    @InjectPresenter
    WeatherFragmentPresenter presenter;

    @BindView(R.id.city_name_tv)
    TextView cityName;

    @BindView(R.id.weather_img)
    WeatherImageView weatherImg;

    @BindView(R.id.temperature_tv)
    TextView temperature;

    @BindView(R.id.weather_update_date_tv)
    TextView weatherUpdateDate;

    @BindView(R.id.wind_speed_tv)
    TextView windSpeed;

    @BindView(R.id.pressure_tv)
    TextView pressure;

    @BindView(R.id.humidity_tv)
    TextView humidity;

    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefreshLayout;

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
        swipeRefreshLayout.setOnRefreshListener(this);
    }

    @Override
    public void showWeather(Weather weather) {

        cityName.setText(weather.getCity().getName().toUpperCase());
        weatherImg.setWeatherImage(weather.getConditionCode());
        temperature.setText(String.valueOf((int) weather.getTemperature()));
        pressure.setText(String.valueOf((int) weather.getPressure()) + " " + getString(R.string.h_pa));
        humidity.setText(String.valueOf((int) weather.getHumidity()) + " %");
        windSpeed.setText(String.valueOf((int) weather.getWindSpeed()) + " " + getString(R.string.m_s));
        weatherUpdateDate.setText(weather.getDateString().toUpperCase());
    }

    @Override
    public void hideSwipeRefresh() {
        if (swipeRefreshLayout != null && swipeRefreshLayout.isRefreshing()) {
            swipeRefreshLayout.setRefreshing(false);
        }
    }

    @Override
    public void onRefresh() {
        presenter.loadWeather();
    }

}
