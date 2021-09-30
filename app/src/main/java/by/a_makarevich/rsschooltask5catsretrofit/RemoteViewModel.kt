package by.a_makarevich.rsschooltask5catsretrofit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import by.a_makarevich.rsschooltask5catsretrofit.data.CatImagesRepository
import by.a_makarevich.rsschooltask5catsretrofit.model.Cat
import kotlinx.coroutines.flow.Flow

class RemoteViewModel(    private val repository: CatImagesRepository = CatImagesRepository.getInstance()
) : ViewModel() {

    fun fetchCatImages(): Flow<PagingData<Cat>> {
        return repository.letCatImagesFlow()
            .cachedIn(viewModelScope)
    }
}