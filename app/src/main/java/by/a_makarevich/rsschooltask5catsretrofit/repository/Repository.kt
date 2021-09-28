package by.a_makarevich.rsschooltask5catsretrofit

import by.a_makarevich.rsschooltask5catsretrofit.data.Cat
import by.a_makarevich.rsschooltask5catsretrofit.repository.WebAccess
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class Repository(){

    fun getFlowListOfCats(): Flow<List<Cat>> = flow {
        emit(WebAccess.catService.getListOfCats().map {
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


