package narek.example.com.yandex_weather_app.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

@Database(entities = {CityEntity.class, WeatherEntity.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract CityDao cityDao();

    public abstract WeatherDao weatherDao();

}
