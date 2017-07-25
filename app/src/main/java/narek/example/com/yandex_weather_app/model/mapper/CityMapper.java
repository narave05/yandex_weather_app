package narek.example.com.yandex_weather_app.model.mapper;


import android.support.annotation.NonNull;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import narek.example.com.yandex_weather_app.model.clean.SuggestCity;
import narek.example.com.yandex_weather_app.model.rest.PlacesResponse;
import narek.example.com.yandex_weather_app.model.rest.Prediction;

public class CityMapper implements Serializable {
    @NonNull
    public List<SuggestCity> buildCity(@NonNull PlacesResponse placesResponse) {
        List<SuggestCity> list = new ArrayList<>();

        for (Prediction p:placesResponse.getPredictions()) {
            list.add(new SuggestCity.CityBuilder()
                    .cityName(p.getDescription())
                    .country(p.getDescription())
                    .cityId(p.getPlaceId())
                    .buildCitySuggest());
        }
        return list;
    }

}
