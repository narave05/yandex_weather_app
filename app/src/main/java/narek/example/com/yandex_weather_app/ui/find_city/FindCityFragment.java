package narek.example.com.yandex_weather_app.ui.find_city;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import butterknife.BindView;
import narek.example.com.yandex_weather_app.R;
import narek.example.com.yandex_weather_app.ui._common.base.MvpBaseFragment;

public class FindCityFragment extends MvpBaseFragment implements FindCityFragmentView{

    @BindView(R.id.find_city_ed)EditText findViewEt;
    @BindView(R.id.recycle_view_cities)RecyclerView recyclerView;

    public static FindCityFragment newInstance() {
        return new FindCityFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_find_city, container, false);
    }
}
