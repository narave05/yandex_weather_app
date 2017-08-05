package narek.example.com.yandex_weather_app.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import javax.inject.Inject;

@Database(entities = {CityEntity.class, WeatherEntity.class}, version = 1)

public abstract class AppDatabase extends RoomDatabase {

    public abstract CityDao cityDao();

    public abstract WeatherDao weatherDao();

}
