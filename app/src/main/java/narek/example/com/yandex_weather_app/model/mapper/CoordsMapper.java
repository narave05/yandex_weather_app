package narek.example.com.yandex_weather_app.model.mapper;

import android.support.annotation.NonNull;

import narek.example.com.yandex_weather_app.model.clean.Coords;
import narek.example.com.yandex_weather_app.model.rest.CoordsResponse;

public class CoordsMapper {
    @NonNull
    public Coords builCoords(@NonNull CoordsResponse response) {
        return new Coords.CoordsBuilder()
                .lat(response.getResult().getGeometry().getLocation().getLat())
                .lon(response.getResult().getGeometry().getLocation().getLng())
                .buildCoords();
    }
}
