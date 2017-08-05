package narek.example.com.yandex_weather_app.di;

import android.arch.persistence.room.Room;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import narek.example.com.yandex_weather_app.db.AppDatabase;

@Module
public class DbModule {
    @Provides
    @Singleton
    public AppDatabase buildDb(Context context){
        return Room.databaseBuilder(context, AppDatabase.class, "weather_db").build();
    }
}
