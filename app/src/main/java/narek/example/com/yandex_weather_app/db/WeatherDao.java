package narek.example.com.yandex_weather_app.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.Single;
import narek.example.com.yandex_weather_app.model.clean.Coords;
import narek.example.com.yandex_weather_app.model.clean.Weather;

@Dao
public interface WeatherDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertWeather(WeatherEntity...weather);

    @Delete
    public void deleteWeather(WeatherEntity... weathers);

    @Query("SELECT * FROM weather WHERE lat IS :lat AND lon IS :lon")
    public Single<WeatherEntity> loadWeather(double lat, double lon);

    @Query("SELECT * FROM weather")
    public Flowable<List<WeatherEntity>> loadAll();

}
