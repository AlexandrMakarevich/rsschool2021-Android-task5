package by.a_makarevich.rsschooltask5catsretrofit


import by.a_makarevich.rsschooltask5catsretrofit.Data.CatsData
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET

interface TheCatApi {
    @GET("images/search?limit=5&page=10&order=DESC")
    fun getListOfCats(): Deferred<Response<List<CatsData>>>
}
