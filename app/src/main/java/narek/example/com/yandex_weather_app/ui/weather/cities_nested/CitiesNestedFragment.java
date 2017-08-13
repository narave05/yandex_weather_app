package narek.example.com.yandex_weather_app.ui.weather.cities_nested;


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
import narek.example.com.yandex_weather_app.adapter.OnItemClickListener;
import narek.example.com.yandex_weather_app.adapter.SimpleCallbackItemTouchHelper;
import narek.example.com.yandex_weather_app.db.CityEntity;
import narek.example.com.yandex_weather_app.ui._common.base.MvpBaseFragment;

public class CitiesNestedFragment extends MvpBaseFragment implements CitiesNestedView, View.OnClickListener {

    private static final float ALPHA_FULL = 1.0f;
    @InjectPresenter
    CitiesNestedPresenter presenter;

    @BindView(R.id.fab)
    FloatingActionButton fab;

    @BindView(R.id.recycler_cities)
    RecyclerView recyclerView;
    private CitiesAdapter citiesAdapter;
    private List<CityEntity> cityListField;

    @ProvidePresenter
    public CitiesNestedPresenter providePresenter() {
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
    public void initAdapter(List<CityEntity> cityList) {
        recyclerView.setAdapter(setCityAdapter(cityList));
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);

        setSwipeForRecyclerView(cityList);
    }

    @Override

    public void setSwipeForRecyclerView(List<CityEntity> cityList) {
        cityListField = cityList;

        ItemTouchHelper.SimpleCallback callback = new SimpleCallbackItemTouchHelper(0, ItemTouchHelper.LEFT) {
            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
            }

            @Override
            public int getSwipeDirs(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
                int position = viewHolder.getAdapterPosition();

                if (position != -1) {

                    if (cityListField.get(position).isActive()) {
                        return 0;
                    } else {

                        presenter.deleteCity(position);
                        citiesAdapter.onDismiss(viewHolder.getAdapterPosition());

                        return super.getSwipeDirs(recyclerView, viewHolder);
                    }
                }
                return 0;
            }
        };

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(callback);
        itemTouchHelper.attachToRecyclerView(recyclerView);
    }

    private CitiesAdapter setCityAdapter(final List<CityEntity> cityList) {
        citiesAdapter = new CitiesAdapter(cityList, new OnItemClickListener() {
            @Override
            public void onItemClick(Object item, int layoutPosition) {
                presenter.updateActiveCity(cityList.get(layoutPosition));
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
