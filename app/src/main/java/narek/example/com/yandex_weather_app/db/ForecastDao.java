package narek.example.com.yandex_weather_app.db;


import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Single;

@Dao
public interface ForecastDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertForecast(List<ForecastEntity> forecastEntities);

    @Delete
    public void deleteForecast(ForecastEntity... forecastEntities);

    @Query("SELECT * FROM forecast WHERE city_id_in_forecast IS :cityId")
    public Single<List<ForecastEntity>> loadForecast(int cityId);
}
