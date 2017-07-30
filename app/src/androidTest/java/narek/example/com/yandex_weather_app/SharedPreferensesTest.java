package narek.example.com.yandex_weather_app;

import android.content.Context;
import android.content.SharedPreferences;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import narek.example.com.yandex_weather_app.data.preferences.PreferenceHelper;

@RunWith(MockitoJUnitRunner.class)
public class SharedPreferensesTest {

    PreferenceHelper preferenceHelper;
    Context mockedContext;

    @Before
    public void setUp() {
        mockedContext = Mockito.mock(Context.class);
        preferenceHelper = PreferenceHelper.getInstance();
    }

    @After
    public void tearDown() {
        preferenceHelper = null;
    }

    @Test
    public void getStoredTimeInterval_returnStoredTimeInterval(){
        SharedPreferences mockedSharedPreference = Mockito.mock(SharedPreferences.class);
        Mockito.when(mockedContext.getSharedPreferences(Mockito.anyString(), Mockito.anyInt())).thenReturn(mockedSharedPreference);
        Mockito.when(mockedSharedPreference.contains(Mockito.anyString())).thenReturn(true);
        Mockito.when(mockedSharedPreference.getInt(Mockito.anyString(), Mockito.anyInt())).thenReturn(7200);
        Assert.assertEquals(7200, preferenceHelper.getIntervalHoursInSeconds());
    }

    @Test
    public void getStoredTime_timeIsNotStored_returnZero(){
        SharedPreferences mockedSharedPreference = Mockito.mock(SharedPreferences.class);
        Mockito.when(mockedContext.getSharedPreferences(Mockito.anyString(), Mockito.anyInt())).thenReturn(mockedSharedPreference);
        Mockito.when(mockedSharedPreference.contains(Mockito.anyString())).thenReturn(false);
        Assert.assertEquals(7200, preferenceHelper.getIntervalHoursInSeconds());
    }
}
