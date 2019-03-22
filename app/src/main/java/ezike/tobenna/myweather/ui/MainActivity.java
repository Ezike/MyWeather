package ezike.tobenna.myweather.ui;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

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
 */
public class MainActivity extends AppCompatActivity {

    private NavController mNavController;

    private ActivityMainBinding mBinding;

    @Inject
    ViewModelProvider.Factory viewModelFactory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        setSupportActionBar(findViewById(R.id.toolbar));

        AndroidInjection.inject(this);

        mNavController = Navigation.findNavController(this, R.id.nav_host_fragment);

        NavigationUI.setupActionBarWithNavController(this, mNavController);
        NavigationUI.setupWithNavController((BottomNavigationView) findViewById(R.id.bottom_nav), mNavController);

        CurrentWeatherViewModel currentWeatherViewModel = ViewModelProviders.of(this, viewModelFactory).get(CurrentWeatherViewModel.class);
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
