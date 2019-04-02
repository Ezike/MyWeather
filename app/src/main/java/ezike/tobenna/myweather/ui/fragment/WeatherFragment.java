package ezike.tobenna.myweather.ui.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.ads.AdRequest;
import com.google.android.material.snackbar.Snackbar;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import ezike.tobenna.myweather.R;
import ezike.tobenna.myweather.data.local.entity.WeatherResponse;
import ezike.tobenna.myweather.databinding.FragmentWeatherBinding;
import ezike.tobenna.myweather.di.Injectable;
import ezike.tobenna.myweather.ui.CurrentWeatherViewModel;
import ezike.tobenna.myweather.utils.Resource;
import ezike.tobenna.myweather.utils.Status;
import ezike.tobenna.myweather.utils.Utilities;
import ezike.tobenna.myweather.widget.WeatherWidgetProvider;

/**
 * A simple {@link Fragment} subclass.
 */
public class WeatherFragment extends Fragment implements Injectable, SwipeRefreshLayout.OnRefreshListener {

    public static final String WIDGET_PREF = "ezike.tobenna.myweather.ui.widget.pref";
    public static final String WIDGET_TEXT = "ezike.tobenna.myweather.ui.widget.text";
    public static final String WIDGET_LOCATION = "ezike.tobenna.myweather.ui.widget.location";
    public static final String WIDGET_ICON = "ezike.tobenna.myweather.ui.widget.icon";

    @Inject
    ViewModelProvider.Factory viewModelFactory;

    private CurrentWeatherViewModel mCurrentWeatherViewModel;

    private FragmentWeatherBinding mBinding;

    private boolean isLoading = true;

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
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_weather, container, false);
        mBinding.setLifecycleOwner(this);

        AdRequest adRequest = new AdRequest.Builder().build();
        mBinding.adView.loadAd(adRequest);

        initViewModel();

        mBinding.swipeRefresh.setOnRefreshListener(this);
        mBinding.swipeRefresh.setColorSchemeColors(
                ContextCompat.getColor(getActivity(), R.color.colorPrimary),
                ContextCompat.getColor(getActivity(), R.color.colorAccent),
                ContextCompat.getColor(getActivity(), R.color.colorPrimaryDark)
        );

        return mBinding.getRoot();
    }

    private void initViewModel() {
        mCurrentWeatherViewModel = ViewModelProviders.of(this, viewModelFactory).get(CurrentWeatherViewModel.class);
        observeWeather();
    }

    private void observeWeather() {
        mCurrentWeatherViewModel.getCurrentWeather().observe(this, currentWeatherResource -> {
            if (currentWeatherResource.data != null) {
                bindData(currentWeatherResource);
                showError(currentWeatherResource);
                showSuccess(currentWeatherResource);
                updateWidgetData(currentWeatherResource.data);

            } else {
                ((AppCompatActivity) Objects.requireNonNull(getActivity())).getSupportActionBar().setTitle("");
            }
            mBinding.setResource(currentWeatherResource);
        });
    }

    private void bindData(@NonNull Resource<WeatherResponse> currentWeatherResource) {
        mBinding.setCondition(currentWeatherResource.data.getCurrent().getCondition());
        mBinding.setWeather(currentWeatherResource.data.getCurrent());
        String location = (currentWeatherResource.data.getLocation().getName() + ", " +
                currentWeatherResource.data.getLocation().getRegion());
        ((AppCompatActivity) Objects.requireNonNull(getActivity())).getSupportActionBar().setTitle(location);
        mBinding.setLocation(currentWeatherResource.data.getLocation());
    }

    private void showError(Resource<WeatherResponse> currentWeatherResource) {
        if (currentWeatherResource.status == Status.ERROR) {
            if (currentWeatherResource.message != null) {
                if (!currentWeatherResource.message.isEmpty()) {
                    showSnackBar(currentWeatherResource.message, v -> snackRetryAction());
                }
            }
        }
    }

    private void showSuccess(Resource<WeatherResponse> currentWeatherResource) {
        if (currentWeatherResource.status == Status.SUCCESS) {
            isLoading = false;
        }
    }

    private void saveToPreferences(WeatherResponse weather) {
        SharedPreferences sharedpreferences = getActivity().getSharedPreferences(WIDGET_PREF, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString(WIDGET_TEXT, weather.getCurrent().getCondition().getText());
        editor.putString(WIDGET_LOCATION, weather.getLocation().getRegion());
        editor.putString(WIDGET_ICON, weather.getCurrent().getCondition().getIcon());
        editor.apply();
    }

    private void updateWidgetData(WeatherResponse weather) {
        saveToPreferences(weather);
        WeatherWidgetProvider.updateWidget(getActivity());
    }

    private void showSnackBar(String message, View.OnClickListener listener) {
        Snackbar.make(mBinding.getRoot(), message, Snackbar.LENGTH_LONG)
                .setAction(R.string.retry, listener)
                .show();
    }

    private void retryFetch() {
        mCurrentWeatherViewModel.retry(String.valueOf(isLoading));
    }

    private boolean isConnected() {
        if (!Utilities.isOnline(Objects.requireNonNull(getActivity()))) {
            showSnackBar(getString(R.string.no_internet), v -> snackRetryAction());
        }
        return true;
    }

    private void snackRetryAction() {
        if (isConnected()) {
            retryFetch();
        }
        isConnected();
    }

    @Override
    public void onPause() {
        if (mBinding.adView != null) {
            mBinding.adView.pause();
        }
        super.onPause();
    }

    @Override
    public void onResume() {
        if (mBinding.adView != null) {
            mBinding.adView.resume();
        }
        super.onResume();
    }

    @Override
    public void onDestroy() {
        if (mBinding.adView != null) {
            mBinding.adView.destroy();
        }
        super.onDestroy();
    }

    @Override
    public void onRefresh() {
        if (isConnected()) {
            retryFetch();
        } else {
            mBinding.swipeRefresh.setRefreshing(false);
            showSnackBar(getString(R.string.no_internet), v -> snackRetryAction());
        }
    }
}