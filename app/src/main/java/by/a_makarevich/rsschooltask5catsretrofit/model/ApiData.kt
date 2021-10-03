package by.a_makarevich.rsschooltask5catsretrofit.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ApiData(
    @Json(name = "id") val id: String,
    @Json(name = "url") val imageUrl: String
)
