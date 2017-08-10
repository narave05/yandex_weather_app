package narek.example.com.yandex_weather_app.ui.weather.cities_nested;


import android.support.v4.app.DialogFragment;

import narek.example.com.yandex_weather_app.ui._common.base.MvpBaseView;

public interface CitiesNestedView extends MvpBaseView{

    void showDialogCitySuggest(DialogFragment dialogFragment);

    void dismissDialog(DialogFragment dialogFragment);
}
