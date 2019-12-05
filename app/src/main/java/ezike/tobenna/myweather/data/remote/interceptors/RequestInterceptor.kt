package ezike.tobenna.myweather.data.remote.interceptors

import ezike.tobenna.myweather.BuildConfig
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import javax.inject.Inject

class RequestInterceptor @Inject constructor(): Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        var request: Request = chain.request()
        val url = request.url.newBuilder()
                .addQueryParameter(ACCESS_KEY, BuildConfig.ApiKey)
                .build()
        request = request.newBuilder().url(url).build()
        return chain.proceed(request)
    }

    companion object {
        const val ACCESS_KEY: String = "access_key"
    }
}