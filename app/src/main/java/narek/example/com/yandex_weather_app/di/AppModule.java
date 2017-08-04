package narek.example.com.yandex_weather_app.di;

import android.app.Application;
import android.arch.persistence.room.Room;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import narek.example.com.yandex_weather_app.App;
import narek.example.com.yandex_weather_app.data.Repository;
import narek.example.com.yandex_weather_app.data.RepositoryImpl;
import narek.example.com.yandex_weather_app.db.AppDatabase;

@Module
public class AppModule {

    @Provides
    @Singleton
    public Repository provideRepository(){
        return new RepositoryImpl();
    }

    @Provides
    @Singleton
    public AppDatabase buildDb(){
        return Room.databaseBuilder(App.getInstance(), AppDatabase.class, "weather_db").build();
    }
}
