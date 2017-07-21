package narek.example.com.yandex_weather_app.ui._common.widget;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;

import narek.example.com.yandex_weather_app.R;

public class WeatherImageView extends AppCompatImageView {


    public WeatherImageView(Context context) {
        super(context);
    }

    public WeatherImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public WeatherImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setWeatherImage(int conditionCode) {
        if (conditionCode >= 200 && conditionCode < 300) {
            setBackground(ContextCompat.getDrawable(getContext(), R.drawable.cloud_storm));
        } else if (conditionCode >= 300 && conditionCode < 400) {
            setBackground(ContextCompat.getDrawable(getContext(), R.drawable.cloud_rain));
        } else if (conditionCode >= 500 && conditionCode < 505) {
            setBackground(ContextCompat.getDrawable(getContext(), R.drawable.sun_cloud_rain));
        } else if (conditionCode >= 500 && conditionCode < 505) {
            setBackground(ContextCompat.getDrawable(getContext(), R.drawable.sun_cloud_rain));
        } else if (conditionCode >= 511 && conditionCode < 531) {
            setBackground(ContextCompat.getDrawable(getContext(), R.drawable.cloud_rain));
        } else if (conditionCode >= 600 && conditionCode < 700) {
            setBackground(ContextCompat.getDrawable(getContext(), R.drawable.snow_cloud_rain));
        } else if (conditionCode == 800) {
            setBackground(ContextCompat.getDrawable(getContext(), R.drawable.sun));
        } else if (conditionCode == 801) {
            setBackground(ContextCompat.getDrawable(getContext(), R.drawable.sun_cloud));
        } else {
            setBackground(ContextCompat.getDrawable(getContext(), R.drawable.cloud));
        }
    }


}
