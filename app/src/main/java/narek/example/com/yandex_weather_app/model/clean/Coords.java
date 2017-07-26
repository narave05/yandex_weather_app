package narek.example.com.yandex_weather_app.model.clean;

/*
 * @author <a href="mailto: alyonamalchikhina@gmail.com">Alena Malchikhina</a>
 * @since 0.1
 */


public class Coords {
    private final double lat;
    private final double lon;

    public Coords(double lat, double lon) {
        this.lat = lat;
        this.lon = lon;
    }

    public double getLat() {
        return lat;
    }

    public double getLon() {
        return lon;
    }

    public static class CoordsBuilder {
        private double lat;
        private double lon;


        public CoordsBuilder lat(double lat){
            this.lat = lat;
            return this;
        }
        public CoordsBuilder lon(double lon){
            this.lon = lon;
            return this;
        }
        public Coords buildCoords(){
            return new Coords(lat, lon);
        }
    }
}
