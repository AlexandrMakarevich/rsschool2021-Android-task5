package by.a_makarevich.rsschooltask5catsretrofit.pagination

import androidx.paging.PagingSource
import by.a_makarevich.rsschooltask5catsretrofit.data.CatImagesRepository.Companion.DEFAULT_PAGE_INDEX
import by.a_makarevich.rsschooltask5catsretrofit.model.Cat
import by.a_makarevich.rsschooltask5catsretrofit.repository.TheCatApi
import java.io.IOException
import retrofit2.HttpException

class CatImagePagingSource(private val theCatApi: TheCatApi) : PagingSource<Int, Cat>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Cat> {
        val page = params.key ?: DEFAULT_PAGE_INDEX

        return try {
            val response = theCatApi.getListOfCats(page, params.loadSize).map {
                Cat(
                    it.id,
                    it.imageUrl
                )
            }
            LoadResult.Page(
                response, prevKey = if (page == DEFAULT_PAGE_INDEX) null else page - 1,
                nextKey = if (response.isEmpty()) null else page + 1
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }
}
