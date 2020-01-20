package ezike.tobenna.myweather.ui

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.github.pwittchen.weathericonview.WeatherIconView
import ezike.tobenna.myweather.R
import ezike.tobenna.myweather.data.Resource
import ezike.tobenna.myweather.data.model.WeatherResponse
import ezike.tobenna.myweather.utils.WeatherIconUtils
import ezike.tobenna.myweather.utils.visible
import java.io.IOException

/**
 * @author tobennaezike
 */

@BindingAdapter("imageUrl")
fun bindImage(imageView: ImageView, imagePath: String) {
    Glide.with(imageView.context)
            .load("http:$imagePath")
            .placeholder(R.drawable.day)
            .into(imageView)
}

@BindingAdapter("showIcon")
fun showIcon(iconView: WeatherIconView, condition: String?) {
    val context = iconView.context
    WeatherIconUtils.getIconResource(context, iconView, condition)
}

@BindingAdapter("showLoading")
fun View.showLoading(resource: Resource<WeatherResponse>?) {
    when (resource) {
        is Resource.Loading -> visibility == View.VISIBLE
        else -> visibility == View.GONE
    }
}

@BindingAdapter("showSuccess")
fun View.showSuccess(boolean: Boolean) {
    visible = boolean
}

@BindingAdapter("showError")
fun TextView.showError(resource: Resource<WeatherResponse>?) {
    when (resource) {
        is Resource.Error -> {
            visible = true
            text = if (resource.error is IOException) {
                context.getString(R.string.data_fetch_failed)
            } else resource.error.localizedMessage
            return
        }
        else -> visibility == View.GONE
    }
}


@BindingAdapter("visibleGone")
fun View.toggleVisibility(boolean: Boolean) {
    when (boolean) {
        true -> visibility == View.VISIBLE
        false -> visibility == View.GONE
    }
}
