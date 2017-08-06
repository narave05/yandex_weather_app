package narek.example.com.yandex_weather_app.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import io.reactivex.Flowable;
import io.reactivex.Single;


@Dao
public interface CityDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertCity(CityEntity... city);

    @Query("DELETE from city WHERE city_name IS :cityName")
    public void deleteCity(String cityName);

    @Query("UPDATE city SET is_active = :isActive WHERE city_name IS :cityName AND lat IS :lat AND lon IS :lon")
    public void updateCurrentCity(String cityName, boolean isActive, double lat, double lon);

    @Query("SELECT * from city WHERE is_active IS 1")
    public Flowable<CityEntity> loadActiveFlowableCity();

    @Query("SELECT * from city WHERE is_active IS 1")
    public Single<CityEntity> getActiveSingleCity();

    @Query("SELECT * from city WHERE is_active IS 1")
    public CityEntity getActiveCityEntity();

    @Query("UPDATE city SET is_active = 0 WHERE city_name IS :cityName")
    public void deactivateCity(String cityName);
}
