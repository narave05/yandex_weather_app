package narek.example.com.yandex_weather_app.data.locale;

import android.content.Context;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import narek.example.com.yandex_weather_app.App;
import narek.example.com.yandex_weather_app.model.clean.Weather;

public class WeatherStorage {

    private static final String FILE_PATH = "/weather";

    public static void save(Weather weather) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(App.getInstance().getCacheDir() + FILE_PATH);
            ObjectOutputStream outputStream = new ObjectOutputStream(fileOutputStream);
            outputStream.writeObject(weather);
            fileOutputStream.flush();
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Weather read() {
        try {
            FileInputStream fileInputStream = new FileInputStream(App.getInstance().getCacheDir() + FILE_PATH);
            ObjectInputStream inputStream = new ObjectInputStream(fileInputStream);
            return (Weather) inputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean hasSavedWeather() {
        File file = new File(App.getInstance().getCacheDir() + FILE_PATH);
        return file.exists();
    }
}
