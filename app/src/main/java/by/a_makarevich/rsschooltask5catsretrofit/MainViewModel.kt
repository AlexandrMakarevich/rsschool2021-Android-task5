package by.a_makarevich.rsschooltask5catsretrofit

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import by.a_makarevich.rsschooltask5catsretrofit.data.Cat
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val repository = Repository()

    private val _mutableCatList = MutableLiveData<List<Cat>>(emptyList<Cat>())
    val catList: LiveData<List<Cat>> get() = _mutableCatList

    private val _mutableLoadingState = MutableLiveData<Boolean>(false)
    val loadingState: LiveData<Boolean> get() = _mutableLoadingState

    fun getCats() {
        viewModelScope.launch {
            _mutableLoadingState.setValue(true)
            repository.getFlowListOfCats().collect {
                _mutableCatList.value = it
                _mutableLoadingState.setValue(false)
            }
        }
    }

}