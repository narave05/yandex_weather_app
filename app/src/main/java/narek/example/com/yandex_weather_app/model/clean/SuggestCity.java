package narek.example.com.yandex_weather_app.model.clean;


public class SuggestCity {
    private final String cityName;
    private final String country;
    private final String cityId;

    public SuggestCity(String cityName, String country, String cityId) {
        this.cityName = cityName;
        this.country = country;
        this.cityId = cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public String getCountry() {
        return country;
    }

    public String getCityId() {
        return cityId;
    }

    public static class CityBuilder {
        private String cityName;
        private String country;
        private String cityId;

        public CityBuilder cityName(String cityName){
            this.cityName = cityName;
            return this;
        }
        public CityBuilder country(String country){
            this.country = country;
            return this;
        }
        public CityBuilder cityId(String cityId){
            this.cityId = cityId;
            return this;
        }

        public SuggestCity buildCitySuggest(){
            return new SuggestCity(cityName, country, cityId);
        }
    }



}
