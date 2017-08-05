package narek.example.com.yandex_weather_app.di;

import android.app.Application;
import android.arch.persistence.room.Room;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import narek.example.com.yandex_weather_app.App;
import narek.example.com.yandex_weather_app.data.Repository;
import narek.example.com.yandex_weather_app.data.RepositoryImpl;
import narek.example.com.yandex_weather_app.db.AppDatabase;

@Module
public class AppModule {

    Context appContext;

    public AppModule(Context appContext) {
        this.appContext = appContext;
    }

    @Provides
    @Singleton
    public Context provideContext(){
        return appContext;
    }

    @Provides
    @Singleton
    public Repository provideRepository(){
        return new RepositoryImpl();
    }

    @Provides
    @Singleton
    public AppDatabase buildDb(Context context){
        return Room.databaseBuilder(context, AppDatabase.class, "weather_db").build();
    }
}
