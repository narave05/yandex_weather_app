package narek.example.com.yandex_weather_app.data.api;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestServiceGenerator {

    private static final int TIMEOUT_SECONDS = 20;

    private static Retrofit.Builder sBuilder = new Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(getHttpClient());

    private static OkHttpClient getHttpClient() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        return new OkHttpClient.Builder()
                .connectTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
                .readTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
                .addInterceptor(logging)
                .build();
    }

    public static <S> S createService(Class<S> serviceClass, String baseUrl) {
        Retrofit retrofit = sBuilder
                .baseUrl(baseUrl)
                .build();
        return retrofit.create(serviceClass);
    }

}
