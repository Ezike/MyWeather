package ezike.tobenna.myweather.di;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;
import ezike.tobenna.myweather.ui.CurrentWeatherViewModel;
import ezike.tobenna.myweather.viewmodel.WeatherViewModelFactory;

/**
 * @author tobennaezike
 */
@Module
abstract class ViewModelModule {

    /*
     * inject this object into a Map using the @IntoMap annotation,
     * with the  CurrentWeatherViewModel.class as key,
     * and a Provider that will build a CurrentWeatherViewModel
     * object.
     *
     * */

    @Binds
    @IntoMap
    @ViewModelKey(CurrentWeatherViewModel.class)
    abstract ViewModel currentWeatherViewModel(CurrentWeatherViewModel currentWeatherViewModel);

    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(WeatherViewModelFactory factory);
}
