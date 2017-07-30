package narek.example.com.yandex_weather_app;


import android.content.Context;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.MockitoRule;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import narek.example.com.yandex_weather_app.data.locale.WeatherStorage;
import narek.example.com.yandex_weather_app.model.clean.City;
import narek.example.com.yandex_weather_app.model.clean.Weather;
import okhttp3.internal.Util;

import static junit.framework.Assert.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class StorageTest {
    private static final String FILE_PATH = "/weather";
    FileOutputStream prevFileOutputStream;
    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    @Mock
    Context context;

    @Mock
    FileOutputStream fileOutputStream;

    @Mock
    FileInputStream fileInputStream;


    @Before
    public void storeCacheState() {

        try {
            prevFileOutputStream = new FileOutputStream(App.getInstance().getCacheDir() + FILE_PATH);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void weatherStorageShouldWriteOnce() {
        try {
            when(context.openFileOutput(anyString(), anyInt())).thenReturn(fileOutputStream);

            WeatherStorage.save(new Weather.WeatherBuilder().city(new City("", 0f, 0f))
                    .weatherUpdateDate(System.currentTimeMillis())
                    .createWeather());
            verify(context, times(1)).openFileOutput(anyString(), anyInt());
            verify(fileOutputStream, atLeast(1)).write(any(byte[].class));

        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }

    @After
    public void returnToPreviousState() {
        fileOutputStream = prevFileOutputStream;
    }
}
