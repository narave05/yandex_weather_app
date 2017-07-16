package narek.example.com.yandex_weather_app.ui.base;

import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.LayoutRes;

import com.arellomobile.mvp.MvpAppCompatActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class MvpBaseActivity extends MvpAppCompatActivity
        implements MvpBaseView {


    protected Unbinder unbinder;

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        unbinder = ButterKnife.bind(this);
    }

    @CallSuper
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @CallSuper
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (unbinder != null) {
            unbinder.unbind();
        }
    }
}
