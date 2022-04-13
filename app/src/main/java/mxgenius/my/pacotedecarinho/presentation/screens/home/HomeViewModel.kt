package mxgenius.my.pacotedecarinho.presentation.screens.home

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import mxgenius.my.pacotedecarinho.domain.model.Clothing
import mxgenius.my.pacotedecarinho.domain.use_cases.UseCases
import mxgenius.my.pacotedecarinho.util.Constants.CATEGORY_ARGUMENT_KEY
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    useCases: UseCases,
    savedStateHandle: SavedStateHandle
): ViewModel() {

    val getAllClothing = useCases.getAllClothingUseCase()
    val getPopularClothing = useCases.getPopularClothingUseCase()

    private val _selectedCategory: MutableStateFlow<Clothing?> = MutableStateFlow(null)
    val selectedCategory: StateFlow<Clothing?> = _selectedCategory

    init {
        viewModelScope.launch(Dispatchers.IO){
            val category = savedStateHandle.get<String>(CATEGORY_ARGUMENT_KEY)
            _selectedCategory.value = category?.let { useCases.getSelectedCategoryUseCase(category = category) }
        }
    }
}
