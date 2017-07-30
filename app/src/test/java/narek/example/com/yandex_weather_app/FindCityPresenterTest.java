package narek.example.com.yandex_weather_app;


import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import narek.example.com.yandex_weather_app.data.RepositoryImpl;
import narek.example.com.yandex_weather_app.ui.find_city.FindCityFragmentView;
import narek.example.com.yandex_weather_app.ui.find_city.FindCityPresenter;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class FindCityPresenterTest {

    @Mock
    FindCityFragmentView view;

    @Mock
    RepositoryImpl repository;

    private FindCityPresenter presenter;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
        presenter = new FindCityPresenter(repository);
        presenter.attachView(view);
    }

    @Test
    public void isKeyBoardInit(){

        presenter.initKeyBoard();
        verify(view, times(1)).openKeyBoard();
    }
}
