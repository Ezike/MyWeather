package ezike.tobenna.myweather.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.snackbar.Snackbar;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

import javax.inject.Inject;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import ezike.tobenna.myweather.R;
import ezike.tobenna.myweather.databinding.FragmentWeatherBinding;
import ezike.tobenna.myweather.di.Injectable;
import ezike.tobenna.myweather.utils.Utilities;

/**
 * A simple {@link Fragment} subclass.
 */
public class WeatherFragment extends Fragment implements Injectable {

    @Inject
    ViewModelProvider.Factory viewModelFactory;

    private CurrentWeatherViewModel mCurrentWeatherViewModel;

    private FragmentWeatherBinding mBinding;

    public WeatherFragment() {
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        isConnected();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_weather, container, false);

        mBinding.setLifecycleOwner(this);

        initViewModel();

        return mBinding.getRoot();
    }

    private void initViewModel() {
        mCurrentWeatherViewModel = ViewModelProviders.of(this, viewModelFactory).get(CurrentWeatherViewModel.class);
        observeWeather();
    }

    private void observeWeather() {

        mCurrentWeatherViewModel.getCurrentWeather().observe(this, currentWeatherResource -> {

            if (currentWeatherResource.data != null) {

                mBinding.setCondition(currentWeatherResource.data.getCurrent().getCondition());
                mBinding.setWeather(currentWeatherResource.data.getCurrent());
                String location = (currentWeatherResource.data.getLocation().getName() + ", " +
                        currentWeatherResource.data.getLocation().getRegion());
                ((AppCompatActivity) Objects.requireNonNull(getActivity())).getSupportActionBar().setTitle(location);
                mBinding.setLocation(currentWeatherResource.data.getLocation());

            }
            mBinding.setResource(currentWeatherResource);
        });
    }

    private boolean isConnected() {

        if (!Utilities.isOnline(Objects.requireNonNull(getActivity()))) {
            showSnackBar(getString(R.string.no_internet));
        }
        return true;
    }

    // Show SnackBar if there's no network or no data available
    private void showSnackBar(String message) {
        Snackbar.make(mBinding.getRoot(), message, Snackbar.LENGTH_LONG)
                .setAction(R.string.retry, v -> {
                    if (isConnected()) {
                        retry();
                    }
                    isConnected();
                })
                .show();
    }

    private void retry() {
    }

}