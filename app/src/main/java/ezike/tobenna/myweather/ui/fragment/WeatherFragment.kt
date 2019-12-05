package ezike.tobenna.myweather.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider.Factory
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.google.android.material.snackbar.Snackbar
import dagger.android.support.AndroidSupportInjection
import ezike.tobenna.myweather.R
import ezike.tobenna.myweather.databinding.FragmentWeatherBinding
import ezike.tobenna.myweather.ui.WeatherViewModel
import ezike.tobenna.myweather.utils.Utilities
import javax.inject.Inject

class WeatherFragment : Fragment(), SwipeRefreshLayout.OnRefreshListener {

    @Inject
    lateinit var viewModelFactory: Factory

    private val weatherViewmodel: WeatherViewModel by viewModels { viewModelFactory }

    private lateinit var binding: FragmentWeatherBinding

    private val isLoading = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidSupportInjection.inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_weather, container, false)
        return binding.getRoot()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = weatherViewmodel
        binding.lifecycleOwner = viewLifecycleOwner
        binding.swipeRefresh.setOnRefreshListener(this)
        binding.swipeRefresh.setColorSchemeColors(
                ContextCompat.getColor(requireContext(), R.color.colorPrimary),
                ContextCompat.getColor(requireContext(), R.color.colorAccent),
                ContextCompat.getColor(requireContext(), R.color.colorPrimaryDark))
    }

    private fun showSnackBar(message: String, listener: OnClickListener) {
        Snackbar.make(binding.root, message, Snackbar.LENGTH_LONG)
                .setAction(R.string.retry, listener)
                .show()
    }

    private fun isConnected(): Boolean {
        return if (!Utilities.isOnline(requireContext())) {
            showSnackBar(getString(R.string.no_internet), OnClickListener {
                snackRetryAction()
            })
            false
        } else true
    }

    private fun snackRetryAction() {
        if (isConnected()) {
            retryFetch()
        }
        isConnected()
    }

    private fun retryFetch() {
        weatherViewmodel.fetchData()
    }

    override fun onRefresh() {
        if (isConnected()) {
            retryFetch()
            binding.swipeRefresh.isRefreshing = isLoading
        }
        binding.swipeRefresh.isRefreshing = false
    }
}