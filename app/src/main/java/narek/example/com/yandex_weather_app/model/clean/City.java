package narek.example.com.yandex_weather_app.model.clean;

import android.support.annotation.NonNull;

import java.io.Serializable;

import narek.example.com.yandex_weather_app.util.Const;

public class City implements Serializable {

    private final String name;
    private Coords coords;
    private String cityPlaceId;

    public String getCityPlaceId() {
        return cityPlaceId;
    }

    public void setCityPlaceId(String cityPlaceId) {
        this.cityPlaceId = cityPlaceId;
    }

    public City(String name, Coords coords, String cityPlaceId) {
        this.name = name == null ? Const.EMPTY_STRING : name;
        this.coords = coords;
        this.cityPlaceId = cityPlaceId;
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
        if (coords != null ? !coords.equals(city.coords) : city.coords != null) return false;
        return cityPlaceId != null ? cityPlaceId.equals(city.cityPlaceId) : city.cityPlaceId == null;

    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (coords != null ? coords.hashCode() : 0);
        result = 31 * result + (cityPlaceId != null ? cityPlaceId.hashCode() : 0);
        return result;
    }

    public static class CityBuilder{
        private String name;
        private Coords coords;
        private String cityPlaceId;

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

        public CityBuilder cityPlaceId(String cityPlaceId) {
            this.cityPlaceId = cityPlaceId;
            return this;
        }

        public City createCity(){
            return new City(name, coords, cityPlaceId);
        }
    }

}
