package by.a_makarevich.rsschooltask5catsretrofit.repository

import by.a_makarevich.rsschooltask5catsretrofit.model.ApiData
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

object WebAccess {

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create())
        .baseUrl("https://api.thecatapi.com/v1/")
        .build()

    val catService: TheCatApi = retrofit.create(TheCatApi::class.java)
}

interface TheCatApi {
    @GET("images/search")
    suspend fun getListOfCats(@Query("page") page: Int, @Query("limit") limit: Int): List<ApiData>
}
