package by.a_makarevich.rsschooltask5catsretrofit.repository

import by.a_makarevich.rsschooltask5catsretrofit.data.ApiData
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

object WebAccess {
    private val retrofit = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create())
        .baseUrl("https://api.thecatapi.com/v1/")
        .build()

     val catService: TheCatApi = retrofit.create(TheCatApi::class.java)
}

interface TheCatApi {
    @GET("images/search?limit=10&page=100&order=DESC")
    suspend fun getListOfCats(): List<ApiData>
}