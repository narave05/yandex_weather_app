package narek.example.com.yandex_weather_app.util;

/*
 * @author <a href="mailto: alyonamalchikhina@gmail.com">Alena Malchikhina</a>
 * @since 0.1
 */


import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;

public class RxBus {
    private static RxBus instance;
    private PublishSubject<Object> subject = PublishSubject.create();

    public static RxBus instanceOf() {
        if (instance == null) {
            instance = new RxBus();
        }
        return instance;
    }

    public void setMessage(Object object) {
        subject.onNext(object);
    }

    public Observable<Object> getEvents() {
        return subject;
    }
}
