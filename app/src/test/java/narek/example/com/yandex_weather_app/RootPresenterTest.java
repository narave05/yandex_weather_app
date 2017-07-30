package narek.example.com.yandex_weather_app;


import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import narek.example.com.yandex_weather_app.ui.root.RootActivityPresenter;
import narek.example.com.yandex_weather_app.ui.root.RootActivityView;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class RootPresenterTest {

    @Mock
    RootActivityView view;

    private RootActivityPresenter presenter;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
        presenter = new RootActivityPresenter();
        presenter.attachView(view);
    }

    @Test
    public void isNavigateToHomeCalled(){
        presenter.onHomeItemClick();
        verify(view, times(1)).openWeatherFragment();
    }

    @Test
    public void isBackPressed(){
        presenter.onBackPressed();
        verify(view, times(1)).hideKeyBoard();
    }

    @Test
    public void isAboutUsItemClicked(){
        presenter.onAboutUsItemClick();
        verify(view, times(1)).openAboutUsFragment();
    }

    @Test
    public void isFindCityItemClicked(){
        presenter.onFindCityItemClick();
        verify(view, times(1)).openFindCityFragment();
    }
    @Test
    public void isSettingsItemClicked(){
        presenter.onSettingsItemClick();
        verify(view, times(1)).openSettingsFragment();
    }
}
