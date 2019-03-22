package ezike.tobenna.myweather.ui;

import android.os.Bundle;
import android.widget.TextView;

import javax.inject.Inject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import dagger.android.AndroidInjection;
import ezike.tobenna.myweather.R;

/**
 * @author tobennaezike
 */
public class MainActivity extends AppCompatActivity {

    TextView texts;
    @Inject
    ViewModelProvider.Factory viewModelFactory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AndroidInjection.inject(this);

        texts = findViewById(R.id.weather);
        CurrentWeatherViewModel currentWeatherViewModel = ViewModelProviders.of(this, viewModelFactory).get(CurrentWeatherViewModel.class);
        currentWeatherViewModel.init();
        currentWeatherViewModel.getCurrentWeather().observe(this, newData -> {
            if (newData.data != null) {
                texts.setText(newData.data.getCondition().getText());
            }
        });
    }
}
