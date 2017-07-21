package narek.example.com.yandex_weather_app.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import narek.example.com.yandex_weather_app.App;

public class NetworkStatusChecker {

    private NetworkStatusChecker() {
    }

    public static boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) App.getInstance().getSystemService(Context
                .CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = connectivityManager.getActiveNetworkInfo();
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
    }
}
