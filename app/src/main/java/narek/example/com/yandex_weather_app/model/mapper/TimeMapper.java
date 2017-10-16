package narek.example.com.yandex_weather_app.model.mapper;

import android.support.annotation.NonNull;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class TimeMapper implements Serializable {
    private static final String pattern = "EE HH:mm";
    private SimpleDateFormat format = new SimpleDateFormat(pattern, Locale.getDefault());

    @NonNull
    public String transformToString(@NonNull Date date) {
        return format.format(date);
    }
}
