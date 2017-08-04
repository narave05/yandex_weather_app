package narek.example.com.yandex_weather_app.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;


@Dao
public interface CityDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertCity(CityEntity...city);

    @Delete
    public void deleteCity(CityEntity...city);

    @Update
    public void updateCurrentCity(CityEntity city);

    @Query("SELECT * from CityEntity WHERE isActive IS 1")
    public CityEntity loadCity();
}
