package ezike.tobenna.myweather.data.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Request(
        @Json(name = "language")
        val language: String,
        @Json(name = "query")
        val query: String,
        @Json(name = "type")
        val type: String,
        @Json(name = "unit")
        val unit: String
)