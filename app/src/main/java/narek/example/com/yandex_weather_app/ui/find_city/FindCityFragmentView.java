package narek.example.com.yandex_weather_app.ui.find_city;

/*
 * @author <a href="mailto: alyonamalchikhina@gmail.com">Alena Malchikhina</a>
 * @since 0.1
 */


import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

import java.util.List;

import narek.example.com.yandex_weather_app.model.clean.SuggestCity;
import narek.example.com.yandex_weather_app.ui._common.base.MvpBaseView;
@StateStrategyType(AddToEndSingleStrategy.class)
public interface FindCityFragmentView extends MvpBaseView{

    void openKeyBoard();
    void showCitiesList(List<SuggestCity> cityList);
    void showError(int message);
}

