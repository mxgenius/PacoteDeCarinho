package mxgenius.my.pacotedecarinho.presentation.screens.details

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import mxgenius.my.pacotedecarinho.domain.model.Clothing
import mxgenius.my.pacotedecarinho.domain.use_cases.UseCases
import mxgenius.my.pacotedecarinho.util.Constants.DETAILS_ARGUMENT_KEY
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val useCases: UseCases,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _selectedClothing: MutableStateFlow<Clothing?> = MutableStateFlow(null)
    val selectedClothing: StateFlow<Clothing?> = _selectedClothing

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val clothingId = savedStateHandle.get<Int>(DETAILS_ARGUMENT_KEY)
            _selectedClothing.value = clothingId?.let { useCases.getSelectedClothingUseCase(clothingId = clothingId) }
        }
    }

    private val _uiEvent = MutableSharedFlow<UiEvent>()
    val uiEvent: SharedFlow<UiEvent> = _uiEvent.asSharedFlow()

    private val _colorPalette: MutableState<Map<String, String>> = mutableStateOf(mapOf())
    val colorPalette: State<Map<String, String>> = _colorPalette

    fun generateColorPalette() {
        viewModelScope.launch {
            _uiEvent.emit(UiEvent.GenerateColorPalette)
        }
    }

    fun setColorPalette(colors: Map<String, String>) {
        _colorPalette.value = colors
    }

}

sealed class UiEvent {
    object GenerateColorPalette : UiEvent()
}