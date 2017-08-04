package narek.example.com.yandex_weather_app.model.clean;


import narek.example.com.yandex_weather_app.App;

public class CoordsModel {
    private Coords coords;

    public Coords getCoords() {
        return coords;
    }

    public void setCoords(Coords coords) {
        this.coords = coords;
        App.getRxBus().setMessage(coords);
    }
}
