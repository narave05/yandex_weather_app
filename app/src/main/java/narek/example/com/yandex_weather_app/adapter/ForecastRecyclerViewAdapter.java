package narek.example.com.yandex_weather_app.adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import narek.example.com.yandex_weather_app.R;
import narek.example.com.yandex_weather_app.model.clean.Forecasts;
import narek.example.com.yandex_weather_app.model.clean.SuggestCity;
import narek.example.com.yandex_weather_app.ui._common.widget.WeatherImageView;
import narek.example.com.yandex_weather_app.util.UnitsConverter;

public class ForecastRecyclerViewAdapter extends RecyclerView.Adapter<ForecastRecyclerViewAdapter.ForecastViewHolder> {

    private static final int MILLIS = 1000;
    private UnitsConverter unitsConverter = new UnitsConverter();
    private List<Forecasts> forecastsList = new ArrayList<>();

    public ForecastRecyclerViewAdapter(List<Forecasts> forecastsList) {
        this.forecastsList = forecastsList;
    }

    @Override
    public ForecastViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View v = LayoutInflater.from(context).inflate(R.layout.forecast_item, parent, false);
        return new ForecastRecyclerViewAdapter.ForecastViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ForecastViewHolder holder, int position) {
        Forecasts forecast = forecastsList.get(position);
        holder.weekDayTv.setText(showWeekday(forecast.getTime()));
        holder.dateTv.setText(String.valueOf(showDate(forecast.getTime())));
        holder.dayTempTv.setText(unitsConverter.convertTemperature((float) forecast.getDayTemp()));
        holder.nightTempTv.setText(unitsConverter.convertTemperature((float) forecast.getNightTemp()));
        holder.iconIv.setWeatherImage(forecast.getIconId());
    }

    private String showDate(long time) {
        Date dNow = new Date(time* MILLIS);
        SimpleDateFormat ft = new SimpleDateFormat("dd.MM.yy", Locale.getDefault());
        return ft.format(dNow);
    }
    private String showWeekday(long time) {
        Date dNow = new Date(time*MILLIS);
        SimpleDateFormat ft = new SimpleDateFormat("E", Locale.getDefault());
        return ft.format(dNow);
    }

    @Override
    public int getItemCount() {
        return forecastsList.size();
    }

    static class ForecastViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.week_day_tv)
        TextView weekDayTv;
        @BindView(R.id.date_tv)TextView dateTv;
        @BindView(R.id.forecast_icon_wiv)WeatherImageView iconIv;
        @BindView(R.id.day_temp_tv)TextView dayTempTv;
        @BindView(R.id.night_temp_tv)TextView nightTempTv;

        ForecastViewHolder(final View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }
    }
}
