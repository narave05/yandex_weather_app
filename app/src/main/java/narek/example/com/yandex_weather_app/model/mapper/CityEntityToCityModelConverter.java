package narek.example.com.yandex_weather_app.model.mapper;


import narek.example.com.yandex_weather_app.db.CityEntity;
import narek.example.com.yandex_weather_app.model.clean.City;
import narek.example.com.yandex_weather_app.model.clean.Coords;

public class CityEntityToCityModelConverter {
    public City makeCityFromCityEntity(CityEntity cityEntity) {
        return new City.CityBuilder()
                .name(cityEntity.getCityName())
                .coords(new Coords.CoordsBuilder()
                .lat(cityEntity.getLat())
                .lon(cityEntity.getLon())
                .buildCoords())
                .createCity();
    }
}
