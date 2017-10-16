package narek.example.com.yandex_weather_app.model.mapper;


import narek.example.com.yandex_weather_app.db.CityEntity;
import narek.example.com.yandex_weather_app.model.clean.City;
import narek.example.com.yandex_weather_app.model.clean.Coords;

public class CityModelToCityEntityConverter {
    public CityEntity makeCityFromCityEntity(City city){
        CityEntity cityEntity = new CityEntity();
        cityEntity.setCityName(city.getName());
        cityEntity.setLat(city.getCoords().getLat());
        cityEntity.setLon(city.getCoords().getLon());
        cityEntity.setActive(false);
        return cityEntity;
    }
}
