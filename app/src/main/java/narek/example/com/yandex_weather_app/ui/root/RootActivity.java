package narek.example.com.yandex_weather_app.ui.root;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;

import butterknife.BindView;
import narek.example.com.yandex_weather_app.App;
import narek.example.com.yandex_weather_app.R;
import narek.example.com.yandex_weather_app.ui._common.base.MvpBaseActivity;
import narek.example.com.yandex_weather_app.ui.abut_us.AbutUsFragment;
import narek.example.com.yandex_weather_app.ui.find_city.FindCityFragment;
import narek.example.com.yandex_weather_app.ui.find_city.FindCityPresenter;
import narek.example.com.yandex_weather_app.ui.settings.SettingsFragment;
import narek.example.com.yandex_weather_app.ui.weather.WeatherFragment;
import narek.example.com.yandex_weather_app.ui.weather.cities_nested.CitiesNestedFragment;
import narek.example.com.yandex_weather_app.util.FragmentTag;
import narek.example.com.yandex_weather_app.util.FragmentUtils;


public class RootActivity extends MvpBaseActivity
        implements RootActivityView, NavigationView.OnNavigationItemSelectedListener {

    @InjectPresenter
    RootActivityPresenter presenter;

    @ProvidePresenter
    public RootActivityPresenter providePresenter(){
        return presenter = App.getInstance().getAppComponent().provideRootActivityPresenter();
    }

    @BindView(R.id.navigation_view)
    NavigationView navigationView;

    @Nullable
    @BindView(R.id.drawer_container)
    DrawerLayout navigationDrawer;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    private FragmentManager fragmentManager;
    private ActionBarDrawerToggle toggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_root_weather);
        fragmentManager = getSupportFragmentManager();

    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.init();
    }

    @Override
    public void setupToolbarAndDrawer() {
        setSupportActionBar(toolbar);

        if (!getResources().getBoolean(R.bool.twoPaneMode)) {
            toggle = new ActionBarDrawerToggle(
                    this,
                    navigationDrawer,
                    toolbar,
                    R.string.navigation_drawer_open,
                    R.string.navigation_drawer_close);
            navigationDrawer.addDrawerListener(toggle);
            toggle.syncState();
        }
            navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.home:
                presenter.onHomeItemClick();
                break;
            case R.id.settings_item:
                presenter.onSettingsItemClick();
                break;
            case R.id.about_us_item:
                presenter.onAboutUsItemClick();
                break;
            case R.id.cities:
                presenter.onCitiesItemClick();
                break;
        }
        if (!getResources().getBoolean(R.bool.twoPaneMode)) {
            navigationDrawer.closeDrawer(Gravity.START);
        }

        return false;
    }

    @Override
    public void openAboutUsFragment() {
        FragmentUtils.openFragment(
                AbutUsFragment.newInstance(),
                fragmentManager,
                FragmentTag.ABOUT,
                true);
    }


    @Override
    public void openSettingsFragment() {
        FragmentUtils.openFragment(
                SettingsFragment.newInstance(),
                fragmentManager,
                FragmentTag.SETTINGS,
                true);
    }

    @Override
    public void openWeatherFragment() {
        FragmentUtils.openFragment(
                WeatherFragment.newInstance(),
                fragmentManager,
                FragmentTag.WEATHER);
    }
    @Override
    public void openCitiesFragment() {
        FragmentUtils.openFragment(
                CitiesNestedFragment.newInstance(),
                fragmentManager,
                FragmentTag.CITIES, true);
    }

    @Override
    public void lockDrawer() {
        if (!getResources().getBoolean(R.bool.twoPaneMode)) {
            navigationDrawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        }
    }


    @Override

    public void unlockDrawer() {
        if (!getResources().getBoolean(R.bool.twoPaneMode)) {
            navigationDrawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
        }

    }

    @Override
    public void changeToolbarIconToArrow() {
        if (!getResources().getBoolean(R.bool.twoPaneMode)) {
            toggle.setDrawerIndicatorEnabled(false);
        }
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onBackPressed();
                }
            });
        }
    }

    @Override
    public void changeToolbarIconToHamburger() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(false);

            if (!getResources().getBoolean(R.bool.twoPaneMode)) {
                toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        navigationDrawer.openDrawer(GravityCompat.START);
                    }
                });

                toggle.setDrawerIndicatorEnabled(true);
                navigationDrawer.addDrawerListener(toggle);
            }
        }
    }

    @Override
    public void setToolBarTitle(@StringRes int titleId) {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(getString(titleId));
        }
    }

    @Override
    public void hideKeyBoard() {
            InputMethodManager inputMethodManager = (InputMethodManager)this.getSystemService(Activity.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(), 0);
    }

    @Override
    public void onBackPressed() {
        presenter.onBackPressed();

        super.onBackPressed();
    }
}
