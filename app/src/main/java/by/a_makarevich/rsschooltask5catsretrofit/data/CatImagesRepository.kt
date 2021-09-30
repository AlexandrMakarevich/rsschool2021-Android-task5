package by.a_makarevich.rsschooltask5catsretrofit.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import by.a_makarevich.rsschooltask5catsretrofit.model.Cat
import by.a_makarevich.rsschooltask5catsretrofit.pagination.CatImagePagingSource
import by.a_makarevich.rsschooltask5catsretrofit.repository.TheCatApi
import by.a_makarevich.rsschooltask5catsretrofit.repository.WebAccess
import kotlinx.coroutines.flow.Flow

class CatImagesRepository(
    private val catApiService: TheCatApi = WebAccess.catService
) {

    fun letCatImagesFlow(pagingConfig: PagingConfig = getDefaultPageConfig()): Flow<PagingData<Cat>> {
        return Pager(
            config = pagingConfig,
            pagingSourceFactory = { CatImagePagingSource(catApiService) }
        ).flow
    }

    private fun getDefaultPageConfig(): PagingConfig {
        return PagingConfig(pageSize = DEFAULT_PAGE_SIZE, enablePlaceholders = false)
    }

    companion object {
        const val DEFAULT_PAGE_INDEX = 1
        const val DEFAULT_PAGE_SIZE = 10

        fun getInstance() = CatImagesRepository()
    }
}

