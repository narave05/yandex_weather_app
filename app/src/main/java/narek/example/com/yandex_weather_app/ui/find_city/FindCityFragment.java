package narek.example.com.yandex_weather_app.ui.find_city;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.jakewharton.rxbinding2.widget.RxTextView;

import java.util.List;

import butterknife.BindView;
import narek.example.com.yandex_weather_app.R;
import narek.example.com.yandex_weather_app.model.clean.City;
import narek.example.com.yandex_weather_app.model.clean.SuggestCity;
import narek.example.com.yandex_weather_app.ui._common.base.MvpBaseFragment;

public class FindCityFragment extends MvpBaseFragment implements FindCityFragmentView{

    @BindView(R.id.find_city_ed)EditText findViewEt;
    @BindView(R.id.recycle_view_cities)RecyclerView recyclerView;

    @InjectPresenter
    FindCityPresenter presenter;

    public static FindCityFragment newInstance() {
        return new FindCityFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_find_city, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter.initKeyBoard();
        presenter.editTextChanged(RxTextView.textChanges(findViewEt));
    }

    @Override
    public void openKeyBoard() {
        InputMethodManager imgr = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imgr.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
    }

    @Override
    public void showCitiesList(List<SuggestCity> cityList) {

    }

    @Override
    public void showError(int message) {

    }
}
