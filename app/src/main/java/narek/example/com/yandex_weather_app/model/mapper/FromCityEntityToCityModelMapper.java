package narek.example.com.yandex_weather_app.model.mapper;


import android.support.annotation.NonNull;

import narek.example.com.yandex_weather_app.db.CityEntity;
import narek.example.com.yandex_weather_app.model.clean.City;

public class FromCityEntityToCityModelMapper {
    @NonNull
    public City transformFromCityEntityToCityModel(@NonNull CityEntity cityEntity) {
        return new City.CityBuilder()
                .coords(cityEntity.getCoords())
                .name(cityEntity.getCityName())
                .createCity();
    }
}
