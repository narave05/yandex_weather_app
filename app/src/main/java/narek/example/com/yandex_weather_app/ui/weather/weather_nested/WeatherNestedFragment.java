package narek.example.com.yandex_weather_app.ui.weather.weather_nested;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.DialogFragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;

import java.util.List;

import butterknife.BindView;
import narek.example.com.yandex_weather_app.App;
import narek.example.com.yandex_weather_app.R;
import narek.example.com.yandex_weather_app.adapter.ForecastRecyclerViewAdapter;
import narek.example.com.yandex_weather_app.adapter.OnItemClickListener;
import narek.example.com.yandex_weather_app.db.CityEntity;
import narek.example.com.yandex_weather_app.model.clean.Forecasts;
import narek.example.com.yandex_weather_app.model.clean.SuggestCity;
import narek.example.com.yandex_weather_app.model.clean.Weather;
import narek.example.com.yandex_weather_app.ui._common.base.MvpBaseFragment;
import narek.example.com.yandex_weather_app.ui._common.widget.WeatherImageView;
import narek.example.com.yandex_weather_app.util.UnitsConverter;


public class WeatherNestedFragment extends MvpBaseFragment implements WeatherNestedView {

    @BindView(R.id.recycler_forecast)RecyclerView recyclerView;

    @InjectPresenter
    WeatherNestedPresenter presenter;

    private ForecastRecyclerViewAdapter adapter;

    @ProvidePresenter
    public WeatherNestedPresenter providePresenter(){
        return presenter = App.getInstance().getAppComponent().provideWeatherNestedPresenter();
    }

    public WeatherNestedFragment() {
        // Required empty public constructor
    }


    public static WeatherNestedFragment newInstance() {

        return new WeatherNestedFragment();
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }
    public void initAdapter(List<Forecasts> forecastsList) {

        /*recyclerView.setAdapter(new ForecastRecyclerViewAdapter(forecastsList, new OnItemClickListener() {
            @Override
            public void onItemClick(Object item, int layoutPosition) {

            }
        }));

        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(manager);*/

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_weather_nested, container, false);
    }


    @Override
    public void showForecast(@NonNull List<Forecasts> forecast) {
        initAdapter(forecast);
    }

    @Override
    public void showError(@StringRes int message) {
        if (getActivity() != null) {
            Toast.makeText(getActivity(), getString(message), Toast.LENGTH_SHORT).show();
        }
    }
}
