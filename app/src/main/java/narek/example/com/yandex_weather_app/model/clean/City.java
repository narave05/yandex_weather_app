package narek.example.com.yandex_weather_app.model.clean;

import android.support.annotation.NonNull;

import java.io.Serializable;

import narek.example.com.yandex_weather_app.util.Const;

public class City implements Serializable {

    private final String name;
    private Coords coords;


    public City(String name, Coords coords) {
        this.name = name == null ? Const.EMPTY_STRING : name;
        this.coords = coords;
    }


    @NonNull
    public String getName() {
        return name;
    }

    @NonNull
    public Coords getCoords(){
        return coords;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof City)) return false;

        City city = (City) o;

        if (name != null ? !name.equals(city.name) : city.name != null) return false;
        return coords != null ? coords.equals(city.coords) : city.coords == null;

    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (coords != null ? coords.hashCode() : 0);
        return result;
    }

    public static class CityBuilder{
        private String name;
        private Coords coords;

        public CityBuilder() {
        }

        public CityBuilder name(String name) {
            this.name = name;
            return this;
        }

        public CityBuilder coords(Coords coords) {
            this.coords = coords;
            return this;
        }

        public City createCity(){
            return new City(name, coords);
        }
    }

}
