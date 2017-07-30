package narek.example.com.yandex_weather_app;

import com.google.gson.Gson;

import org.junit.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Single;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import io.reactivex.observers.TestObserver;
import narek.example.com.yandex_weather_app.data.api.PlacesApi;
import narek.example.com.yandex_weather_app.model.clean.City;
import narek.example.com.yandex_weather_app.model.clean.Coords;
import narek.example.com.yandex_weather_app.model.clean.SuggestCity;
import narek.example.com.yandex_weather_app.model.clean.Weather;
import narek.example.com.yandex_weather_app.model.mapper.CityMapper;
import narek.example.com.yandex_weather_app.model.mapper.CoordsMapper;
import narek.example.com.yandex_weather_app.model.rest.CoordsResponse;
import narek.example.com.yandex_weather_app.model.rest.PlacesResponse;
import narek.example.com.yandex_weather_app.model.rest.Prediction;
import narek.example.com.yandex_weather_app.model.rest.WeatherDataRes;

import static narek.example.com.yandex_weather_app.R.string.weather;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class RepositoryCitySuggestsTest {

    @Mock
    PlacesApi placesApi;
    private PlacesResponse placesResponse;
    private CoordsResponse coordsResponse;
    private List<SuggestCity> suggestCityList = new ArrayList<>();
    private Coords coords;
    private String placesJson = JsonProvider.getInstance().getCitiesJson();
    private String coordsJson = JsonProvider.getInstance().getCoordsJson();

    @Before
    public void init(){
        Gson json = new Gson();
        placesResponse = json.fromJson(placesJson, PlacesResponse.class);
        coordsResponse = json.fromJson(coordsJson, CoordsResponse.class);

        for (Prediction p : placesResponse.getPredictions()) {
            suggestCityList.add(new SuggestCity.CityBuilder()
                    .cityId(p.getPlaceId())
                    .cityName(p.getDescription())
                    .country(p.getDescription())
                    .buildCitySuggest()
            );
        }
        coords = new Coords.CoordsBuilder()
                .lat(coordsResponse.getResult().getGeometry().getLocation().getLat())
                .lon(coordsResponse.getResult().getGeometry().getLocation().getLng())
                .buildCoords();

    }

    @Test
    public void isCitySuggestionsMappedWell(){
        when(placesApi.callPlacesSuggestions(anyString())).thenReturn(Single.just(placesResponse));
        TestObserver<List<SuggestCity>> observer = placesApi.callPlacesSuggestions(anyString())
                .map(new Function<PlacesResponse, List<SuggestCity>>() {
                    @Override
                    public List<SuggestCity> apply(@NonNull PlacesResponse placesResponse) throws Exception {
                        return new CityMapper().buildCity(placesResponse);
                    }
                }).test();
        observer.assertNoErrors().assertValue(suggestCityList);
    }

    @Test
    public void isCoordsMappedWell(){
        when(placesApi.callCordsByCityId(anyString())).thenReturn(Single.just(coordsResponse));
        TestObserver<Coords> observer = placesApi.callCordsByCityId(anyString())
                .map(new Function<CoordsResponse, Coords>() {
                    @Override
                    public Coords apply(@NonNull CoordsResponse coordsResponse) throws Exception {
                        return new CoordsMapper().builCoords(coordsResponse);
                    }
                }).test();
        observer.assertNoErrors().assertValue(coords);
    }
}
