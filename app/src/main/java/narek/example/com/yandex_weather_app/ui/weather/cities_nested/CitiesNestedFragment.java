package narek.example.com.yandex_weather_app.ui.weather.cities_nested;


import android.graphics.Canvas;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;

import java.util.List;

import butterknife.BindView;
import narek.example.com.yandex_weather_app.App;
import narek.example.com.yandex_weather_app.R;
import narek.example.com.yandex_weather_app.adapter.CitiesAdapter;
import narek.example.com.yandex_weather_app.adapter.ItemTouchHelperAdapter;
import narek.example.com.yandex_weather_app.adapter.OnItemClickListener;
import narek.example.com.yandex_weather_app.adapter.SimpleCallbackItemTouchHelper;
import narek.example.com.yandex_weather_app.db.CityEntity;
import narek.example.com.yandex_weather_app.model.clean.City;
import narek.example.com.yandex_weather_app.ui._common.base.MvpBaseFragment;

public class CitiesNestedFragment extends MvpBaseFragment implements CitiesNestedView, View.OnClickListener {

    private static final float ALPHA_FULL = 1.0f;
    @InjectPresenter
    CitiesNestedPresenter presenter;

    @BindView(R.id.fab)FloatingActionButton fab;

    @BindView(R.id.recycler_cities)
    RecyclerView recyclerView;
    private CitiesAdapter citiesAdapter;

    @ProvidePresenter
    public CitiesNestedPresenter providePresenter(){
        return App.getInstance().getAppComponent().provideCitiesNestedPresenter();
    }

    public CitiesNestedFragment() {
        // Required empty public constructor
    }
    public static CitiesNestedFragment newInstance() {
        return new CitiesNestedFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_cities_nested, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        fab.setOnClickListener(this);
    }

    @Override
    public void showDialogCitySuggest(DialogFragment dialogFragment) {
        dialogFragment.show(getChildFragmentManager(), null);

    }

    @Override
    public void dismissDialog(DialogFragment dialogFragment) {
        dialogFragment.dismiss();
    }

    @Override
    public void initAdapter(List<City> cityList) {
        recyclerView.setAdapter(setCityAdapter(cityList));
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
        presenter.geActiveCityId();
    }

    @Override
    public void setSwipeForRecyclerView(final CityEntity cityEntity, final List<City> cityList) {
        ItemTouchHelper.SimpleCallback callback = new SimpleCallbackItemTouchHelper(0, ItemTouchHelper.LEFT) {
            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                presenter.deleteCity(viewHolder.getAdapterPosition());
                citiesAdapter.onDismiss(viewHolder.getAdapterPosition());

            }
        };

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(callback);
        itemTouchHelper.attachToRecyclerView(recyclerView);
    }

    private CitiesAdapter setCityAdapter(final List<City> cityList){
        citiesAdapter = new CitiesAdapter(cityList, new OnItemClickListener() {
            @Override
            public void onItemClick(Object item, int layoutPosition) {
                presenter.upDateActiveCity(cityList.get(layoutPosition));
            }
        });
        return citiesAdapter;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fab:
                presenter.fabClicked();
                break;
        }
    }
}