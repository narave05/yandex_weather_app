package narek.example.com.yandex_weather_app.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import io.reactivex.Flowable;


@Dao
public interface CityDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertCity(CityEntity... city);

    @Delete
    public void deleteCity(CityEntity...city);

    @Query("UPDATE city SET isActive = :isActive WHERE city_name IS :cityName AND lat IS :lat AND lon IS :lon")
    public void updateCurrentCity(String cityName, boolean isActive, double lat, double lon);

    @Query("SELECT * from city WHERE is_active IS 1")
    public Flowable<CityEntity> loadCity();
}
