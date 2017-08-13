package narek.example.com.yandex_weather_app.ui.find_city;


import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatDialogFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.jakewharton.rxbinding2.widget.RxTextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import narek.example.com.yandex_weather_app.App;
import narek.example.com.yandex_weather_app.R;
import narek.example.com.yandex_weather_app.adapter.OnItemClickListener;
import narek.example.com.yandex_weather_app.adapter.SuggestionAdapter;
import narek.example.com.yandex_weather_app.model.clean.SuggestCity;
import narek.example.com.yandex_weather_app.ui._common.base.MvpBaseFragment;

public class FindCityFragment extends MvpAppCompatDialogFragment implements FindCityFragmentView{

    @BindView(R.id.find_city_ed)EditText findViewEt;
    @BindView(R.id.recycle_view_cities)RecyclerView recyclerView;
    @BindView(R.id.progress_bar)
    ProgressBar progressBar;
    @InjectPresenter
    FindCityPresenter presenter;
    Unbinder unbinder;

    @ProvidePresenter
    public FindCityPresenter providePresenter(){
        return presenter = App.getInstance().getAppComponent().provideFindCityPresenter();
    }

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
        unbinder = ButterKnife.bind(this, view);
        presenter.initKeyBoard();
        presenter.editTextChanged(RxTextView.textChanges(findViewEt));

    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(this.getClass().getName(), "onResume: i am here");
    }

    @Override
    public void onStart() {
        super.onStart();
        getDialog().getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
    }

    @Override
    public void openKeyBoard() {
        getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
    }

    @Override
    public void showCitiesList(List<SuggestCity> cityList) {
        initAdapter(cityList);
    }

    @Override
    public void showError(int message) {
        if (getActivity() != null) {
            Toast.makeText(getActivity(), getString(message), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    public void initAdapter(List<SuggestCity> cityList) {

        recyclerView.setAdapter(setSuggestionAdapter(cityList));
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(manager);

    }

    private SuggestionAdapter setSuggestionAdapter(final List<SuggestCity> cityList) {

        return new SuggestionAdapter(cityList, new OnItemClickListener() {
            @Override
            public void onItemClick(Object item, int layoutPosition) {
                presenter.callForCoords(cityList.get(layoutPosition));
            }
        });
    }
    @CallSuper
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (unbinder != null) {
            unbinder.unbind();
        }
    }

}
