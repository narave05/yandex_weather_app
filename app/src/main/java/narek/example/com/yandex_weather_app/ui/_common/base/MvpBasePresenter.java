package narek.example.com.yandex_weather_app.ui._common.base;

import com.arellomobile.mvp.MvpPresenter;
import com.arellomobile.mvp.MvpView;

import io.reactivex.disposables.CompositeDisposable;

public abstract class MvpBasePresenter<V extends MvpView> extends MvpPresenter<V> {

    protected final CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Override
    public void onDestroy() {
        super.onDestroy();
        compositeDisposable.clear();
    }
}
