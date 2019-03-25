package ezike.tobenna.myweather.ui;

import android.os.Bundle;

import javax.inject.Inject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;
import dagger.android.AndroidInjection;
import ezike.tobenna.myweather.R;
import ezike.tobenna.myweather.databinding.ActivityMainBinding;


/**
 * @author tobennaezike
 * @since 16/03/19
 */
public class MainActivity extends AppCompatActivity {

    @Inject
    ViewModelProvider.Factory viewModelFactory;

    private NavController mNavController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        AndroidInjection.inject(this);

        binding.setLifecycleOwner(this);

        setSupportActionBar(binding.toolbar);

        binding.toolbar.setTitle("");

        mNavController = Navigation.findNavController(this, R.id.nav_host_fragment);

        NavigationUI.setupActionBarWithNavController(this, mNavController);

        NavigationUI.setupWithNavController(binding.bottomNav, mNavController);

        CurrentWeatherViewModel currentWeatherViewModel =
                ViewModelProviders.of(this, viewModelFactory).get(CurrentWeatherViewModel.class);

        currentWeatherViewModel.init();
        observeWeather(currentWeatherViewModel);
    }

    private void observeWeather(CurrentWeatherViewModel currentWeatherViewModel) {
        currentWeatherViewModel.getCurrentWeather().observe(this, newData -> {
            if (newData.data != null) {
                //
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        return NavigationUI.navigateUp(mNavController, (DrawerLayout) null);
    }
}
