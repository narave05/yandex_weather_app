package narek.example.com.yandex_weather_app.ui.abut_us;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import narek.example.com.yandex_weather_app.R;
import narek.example.com.yandex_weather_app.ui._common.dummy.DummyFragment;

public class AbutUsFragment extends DummyFragment {

    public static AbutUsFragment newInstance() {
        return new AbutUsFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_abut_us, container, false);
    }
}
