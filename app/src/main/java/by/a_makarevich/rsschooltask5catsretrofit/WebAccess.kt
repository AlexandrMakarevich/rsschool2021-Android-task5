package by.a_makarevich.rsschooltask5catsretrofit

import by.a_makarevich.rsschooltask5catsretrofit.Data.ApiData
import by.a_makarevich.rsschooltask5catsretrofit.Data.Cat
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET


interface TheCatApi {
    @GET("images/search?limit=10&page=100&order=DESC")
    suspend fun getListOfCats(): List<ApiData>
}


object WebAccess {
    private val retrofit = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create())
        .baseUrl("https://api.thecatapi.com/v1/")
        .build()

    private val catService = retrofit.create(TheCatApi::class.java)

    fun getFlowListOfCats(): Flow<List<Cat>> = flow {

        emit(catService.getListOfCats().map {
            Cat(
                it.id,
                it.imageUrl
            )
        })
    }
}


/*   suspend fun getListOfCats(): List<Cat> {
         return withContext(Dispatchers.IO) {
             catService.getListOfCats().map {
                 Cat(
                     it.id,
                     it.imageUrl
                 )
             }
         }
     }*/


