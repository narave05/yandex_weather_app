package narek.example.com.yandex_weather_app.ui.settings;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;

import java.util.Locale;

import butterknife.BindView;
import narek.example.com.yandex_weather_app.App;
import narek.example.com.yandex_weather_app.R;
import narek.example.com.yandex_weather_app.ui._common.base.MvpBaseFragment;
import narek.example.com.yandex_weather_app.util.CustomSeekBarChangeListener;

public class SettingsFragment extends MvpBaseFragment implements SettingsFragmentView {

    @InjectPresenter
    SettingsFragmentPresenter presenter;

    @ProvidePresenter
    public SettingsFragmentPresenter providePresenter(){
        return presenter = App.getInstance().getAppComponent().provideSettingsPresenter();
    }

    @BindView(R.id.interval)
    TextView intervalTextView;

    @BindView(R.id.interval_seek_bar)
    SeekBar intervalSeekBar;

    public static SettingsFragment newInstance() {
        return new SettingsFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_settings, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter.init();
        intervalSeekBar.setOnSeekBarChangeListener(new CustomSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                presenter.onCurrentProgressChanged(seekBar.getProgress());
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                presenter.onCurrentIntervalChanged(seekBar.getProgress());
            }
        });
    }

    @Override
    public void setProgressText(int currentInterval) {
        intervalTextView.setText(String.format(Locale.getDefault(), getString(R.string.Interval_is), currentInterval));
    }

    @Override
    public void updatedProgress(int currentInterval) {
        intervalSeekBar.setProgress(currentInterval);
    }
}
