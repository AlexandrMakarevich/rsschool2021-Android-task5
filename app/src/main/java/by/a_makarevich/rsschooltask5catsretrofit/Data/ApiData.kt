package by.a_makarevich.rsschooltask5catsretrofit.Data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CatsData(
    @Json(name = "id") val id: String,
    @Json(name = "url") val imageUrl: String,
    @Json(name = "width") val width: Int,
    @Json(name = "height") val height: Int
)
