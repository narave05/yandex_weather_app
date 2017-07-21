package narek.example.com.yandex_weather_app.ui.root;

import android.os.Bundle;
import android.support.annotation.NonNull;
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

import com.arellomobile.mvp.presenter.InjectPresenter;

import butterknife.BindView;
import narek.example.com.yandex_weather_app.R;
import narek.example.com.yandex_weather_app.ui._common.base.MvpBaseActivity;
import narek.example.com.yandex_weather_app.ui.abut_us.AbutUsFragment;
import narek.example.com.yandex_weather_app.ui.settings.SettingsFragment;
import narek.example.com.yandex_weather_app.ui.weather.WeatherFragment;
import narek.example.com.yandex_weather_app.util.FragmentTag;
import narek.example.com.yandex_weather_app.util.FragmentUtils;


public class RootActivity extends MvpBaseActivity
        implements RootActivityView, NavigationView.OnNavigationItemSelectedListener {

    @InjectPresenter
    RootActivityPresenter presenter;

    @BindView(R.id.navigation_view)
    NavigationView navigationView;

    @BindView(R.id.drawer_container)
    DrawerLayout navigationDrawer;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    private FragmentManager fragmentManager;
    private ActionBarDrawerToggle toggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_root_weather);
        fragmentManager = getSupportFragmentManager();
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.init();
    }

    @Override
    public void setupToolbarAndDrawer() {
        setSupportActionBar(toolbar);
        toggle = new ActionBarDrawerToggle(
                this,
                navigationDrawer,
                toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
        navigationDrawer.addDrawerListener(toggle);
        toggle.syncState();
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
        }
        navigationDrawer.closeDrawer(Gravity.START);
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
    public void lockDrawer() {
        navigationDrawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
    }


    @Override
    public void unlockDrawer() {
        navigationDrawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
    }

    @Override
    public void changeToolbarIconToArrow() {
        toggle.setDrawerIndicatorEnabled(false);
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
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    navigationDrawer.openDrawer(GravityCompat.START);
                }
            });
        }
        toggle.setDrawerIndicatorEnabled(true);
        navigationDrawer.addDrawerListener(toggle);
    }

    @Override
    public void setToolBarTitle(@StringRes int titleId) {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(getString(titleId));
        }
    }

    @Override
    public void onBackPressed() {
        presenter.onBackPressed();

        super.onBackPressed();
    }

}
