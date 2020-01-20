package ezike.tobenna.myweather.data

sealed class Resource<T>(
        val data: T? = null,
        val message: String? = null
) {
    data class Success<T>(val t: T) : Resource<T>(t)
    data class Loading<T>(val t: T? = null) : Resource<T>(t)
    data class Error<T>(val error: Throwable, val t: T? = null) : Resource<T>(t, error.message)

}