package narek.example.com.yandex_weather_app.ui.weather;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.DialogFragment;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;

import java.util.List;

import butterknife.BindView;
import narek.example.com.yandex_weather_app.App;
import narek.example.com.yandex_weather_app.R;
import narek.example.com.yandex_weather_app.adapter.ForecastRecyclerViewAdapter;
import narek.example.com.yandex_weather_app.model.clean.Forecasts;
import narek.example.com.yandex_weather_app.model.clean.Weather;
import narek.example.com.yandex_weather_app.ui._common.base.MvpBaseFragment;
import narek.example.com.yandex_weather_app.ui._common.widget.WeatherImageView;
import narek.example.com.yandex_weather_app.util.UnitsConverter;


public class WeatherFragment extends MvpBaseFragment implements WeatherFragmentView, SwipeRefreshLayout.OnRefreshListener, AppBarLayout.OnOffsetChangedListener {

    @InjectPresenter
    WeatherFragmentPresenter presenter;

    @ProvidePresenter
    public WeatherFragmentPresenter providePresenter() {
        return presenter = App.getInstance().getAppComponent().provideWeatherPresenter();
    }

    @BindView(R.id.city_name_tv)
    TextView cityName;

    @BindView(R.id.swipe_refresh_we)SwipeRefreshLayout
    swipeRefreshLayout;

    @BindView(R.id.weatherImageView)
    WeatherImageView weatherImg;

    @BindView(R.id.temperature_tv)
    TextView temperature;

    @BindView(R.id.recycler_forecast)
    RecyclerView recyclerView;

    @BindView(R.id.humidity_tv)TextView humidityTv;

    @BindView(R.id.wind_speed_tv)TextView windTv;

    @Nullable
    @BindView(R.id.app_bar_weather)AppBarLayout appBarLayout;

    @BindView(R.id.weather_update_date_tv)TextView updateTimeTV;

    @BindView(R.id.relative_collapse)
    RelativeLayout relativeLayout;

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
    public void openCitiesFragment(DialogFragment suggestFragment) {
        suggestFragment.show(getChildFragmentManager(), null);
    }

    @Override
    public void closeCitiesFragment(DialogFragment suggestFragment) {
        suggestFragment.dismiss();
    }

    @Override
    public void showForecast(List<Forecasts> forecast) {
        initAdapter(forecast);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (!getResources().getBoolean(R.bool.twoPaneMode)) {
            if (appBarLayout != null) {
                appBarLayout.addOnOffsetChangedListener(this);
            }

        }
    }

    @Override
    public void showWeather(@NonNull Weather weather, String cityName) {

        this.cityName.setText(cityName);
        weatherImg.setWeatherImage(weather.getConditionCode());
        temperature.setText(new UnitsConverter().convertTemperature(weather.getTemperature()));
        humidityTv.setText(String.valueOf((int) weather.getHumidity()) + " %");
        windTv.setText(String.valueOf((int) weather.getWindSpeed()) + " " + getString(R.string.m_s));
        updateTimeTV.setText(new UnitsConverter().convertTime(System.currentTimeMillis()));
    }
    @Override
    public void showError(@StringRes int message) {
        if (getActivity() != null) {
            Toast.makeText(getActivity(), getString(message), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onRefresh() {
        presenter.loadForecastFromInternet();
        presenter.loadWeatherFromInternet();
    }

    @Override
    public void onPause() {
        super.onPause();
        if (!getResources().getBoolean(R.bool.twoPaneMode)) {
            if (appBarLayout != null) {
                appBarLayout.removeOnOffsetChangedListener(this);
            }

        }
    }

    @Override
    public void hideSwipe() {
        swipeRefreshLayout.setRefreshing(false);
    }
    public void initAdapter(List<Forecasts> forecastsList) {

        recyclerView.setAdapter(new ForecastRecyclerViewAdapter(forecastsList));

        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(manager);

    }

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int i) {
        if (!getResources().getBoolean(R.bool.twoPaneMode)) {
            if (i == 0) {
                swipeRefreshLayout.setEnabled(true);
            } else {
                swipeRefreshLayout.setEnabled(false);
            }

            relativeLayout.setAlpha(1.0f - Math.abs(i / (float)
                    appBarLayout.getTotalScrollRange()));
        }
    }
}
