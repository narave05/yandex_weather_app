package narek.example.com.yandex_weather_app.data.api;

/*
 * @author <a href="mailto: alyonamalchikhina@gmail.com">Alena Malchikhina</a>
 * @since 0.1
 */


import io.reactivex.Single;
import narek.example.com.yandex_weather_app.model.rest.CoordsResponse;
import narek.example.com.yandex_weather_app.model.rest.PlacesResponse;
import narek.example.com.yandex_weather_app.model.rest.WeatherDataRes;
import narek.example.com.yandex_weather_app.util.AppConfig;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class PlacesApi {

    private final String types = "(cities)";
    PlacesApi.PlacesRestService placesRestService = RestServiceGenerator.createService(PlacesApi.PlacesRestService.class,
            AppConfig.BASE_URL_API_PLACES);

    public Single<PlacesResponse> callPlacesSuggestions(String cityName) {
        return placesRestService.callPlacesByName( cityName, types, AppConfig.API_KEY_PLACES);
    }
    public Single<CoordsResponse> callCordsByCityId(String coords) {
        return placesRestService.callCoordsByCityId(coords, AppConfig.API_KEY_PLACES);
    }

    interface PlacesRestService {
        @GET("autocomplete/json")
        Single<PlacesResponse> callPlacesByName(@Query("input") String input, @Query("types") String types,
                                                @Query("key") String key);

        @GET("details/json")
        Single<CoordsResponse> callCoordsByCityId(@Query("placeid") String placeid, @Query("key") String key);
    }

}
