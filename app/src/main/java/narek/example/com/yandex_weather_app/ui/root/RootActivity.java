package narek.example.com.yandex_weather_app.ui.root;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;

import narek.example.com.yandex_weather_app.R;
import narek.example.com.yandex_weather_app.ui.abut_us.AbutUsFragment;
import narek.example.com.yandex_weather_app.ui.settings.SettingsFragment;
import narek.example.com.yandex_weather_app.ui.weather.WeatherFragment;
import narek.example.com.yandex_weather_app.utils.FragmentTag;
import narek.example.com.yandex_weather_app.utils.FragmentUtils;


public class RootActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private FragmentManager fragmentManager;
    private DrawerLayout navigationDrawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_root_weather);
        fragmentManager = getSupportFragmentManager();
        setupToolbarAndDrawer();
        FragmentUtils.openFragment(
                WeatherFragment.newInstance(),
                fragmentManager,
                FragmentTag.WEATHER);
    }

    private void setupToolbarAndDrawer() {
        NavigationView navigationView = (NavigationView) findViewById(R.id.navigation_view);
        navigationDrawer = (DrawerLayout) findViewById(R.id.drawer_container);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
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
        item.setChecked(true);
        switch (item.getItemId()) {
            case R.id.home:
                openWeatherFragment();
                break;
            case R.id.settings_item:
                openSettingsFragment();
                break;
            case R.id.about_us_item:
                openAboutUsFragment();
                break;
        }
        navigationDrawer.closeDrawer(Gravity.START);
        return false;
    }


    private void openAboutUsFragment() {
        FragmentUtils.openFragment(
                AbutUsFragment.newInstance(),
                fragmentManager,
                FragmentTag.ABOUT,
                true);
    }

    private void openSettingsFragment() {
        FragmentUtils.openFragment(
                SettingsFragment.newInstance(),
                fragmentManager,
                FragmentTag.SETTINGS,
                true);
    }

    private void openWeatherFragment() {
        FragmentUtils.openFragment(
                WeatherFragment.newInstance(),
                fragmentManager,
                FragmentTag.WEATHER,
                true);
    }

    @Override
    public void onBackPressed() {
        if (!FragmentUtils.isFragmentExist(fragmentManager, FragmentTag.WEATHER)) {
            openWeatherFragment();
        } else {
            super.onBackPressed();
        }
    }
}
