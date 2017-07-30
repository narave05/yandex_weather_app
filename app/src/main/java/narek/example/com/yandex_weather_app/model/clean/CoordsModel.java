package narek.example.com.yandex_weather_app.model.clean;

/*
 * @author <a href="mailto: alyonamalchikhina@gmail.com">Alena Malchikhina</a>
 * @since 0.1
 */


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
