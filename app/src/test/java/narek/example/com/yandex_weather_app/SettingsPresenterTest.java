package narek.example.com.yandex_weather_app;


import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import narek.example.com.yandex_weather_app.data.RepositoryImpl;
import narek.example.com.yandex_weather_app.ui.settings.SettingsFragmentPresenter;
import narek.example.com.yandex_weather_app.ui.settings.SettingsFragmentView;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class SettingsPresenterTest {
    @Mock
    SettingsFragmentView view;

    @Mock
    RepositoryImpl repository;

    private SettingsFragmentPresenter presenter;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
        presenter = new SettingsFragmentPresenter(repository);
        presenter.attachView(view);
    }

    @Test
    public void isCurrentIntervalChanged(){
        presenter.onCurrentIntervalChanged(anyInt());
        verify(repository, times(1)).saveUpdateInterval(anyInt());
    }

    @Test
    public void isCurrentProgressChanged(){
        presenter.onCurrentProgressChanged(anyInt());
        verify(view, times(1)).setProgressText(anyInt());
    }

    @Test
    public void isPresenterInit(){
        presenter.init();
        verify(view, times(1)).setProgressText(anyInt());
        verify(view, times(1)).updatedProgress(anyInt());
    }
}
