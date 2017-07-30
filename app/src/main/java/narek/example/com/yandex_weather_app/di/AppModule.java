package narek.example.com.yandex_weather_app.di;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import narek.example.com.yandex_weather_app.data.Repository;
import narek.example.com.yandex_weather_app.data.RepositoryImpl;

@Module
public class AppModule {

    @Provides
    @Singleton
    public Repository provideRepository(){
        return new RepositoryImpl();
    }
}
