package narek.example.com.yandex_weather_app.model.clean;

public class City {
    private final String name;
    private final float lon;
    private final float lat;

    public City(String name, float lon, float lat) {
        this.name = name;
        this.lon = lon;
        this.lat = lat;
    }

    public String getName() {
        return name;
    }

    public float getLongitude() {
        return lon;
    }

    public float getLatitude() {
        return lat;
    }
}
