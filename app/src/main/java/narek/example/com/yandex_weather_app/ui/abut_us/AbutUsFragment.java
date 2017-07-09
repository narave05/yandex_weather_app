package narek.example.com.yandex_weather_app.ui.abut_us;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import narek.example.com.yandex_weather_app.R;

public class AbutUsFragment extends Fragment {

    public static AbutUsFragment newInstance() {
        AbutUsFragment fragment = new AbutUsFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_abut_us, container, false);
    }

}
