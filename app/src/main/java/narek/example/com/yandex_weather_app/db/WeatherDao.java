package narek.example.com.yandex_weather_app.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import narek.example.com.yandex_weather_app.model.clean.Weather;

@Dao
public interface WeatherDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertWeather(WeatherEntity...weather);

    @Delete
    public void deleteWeather(WeatherEntity... weathers);

    @Query("SELECT * FROM WeatherEntity WHERE cityId IS city_id")
    public List<WeatherEntity> loadWeather(String city_id);

    @Query("SELECT * FROM WeatherEntity")
    public List<WeatherEntity> loadAll();

}
