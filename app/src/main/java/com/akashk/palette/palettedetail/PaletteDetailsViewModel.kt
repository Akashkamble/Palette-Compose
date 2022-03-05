package com.akashk.palette.palettedetail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.akashk.palette.destinations.PaletteDetailScreenDestination
import com.akashk.palette.domain.data.Palette
import com.akashk.palette.domain.data.PaletteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

@HiltViewModel
class PaletteDetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val paletteRepository: PaletteRepository
) : ViewModel() {

    private var selectedPalette: Palette = PaletteDetailScreenDestination
        .argsFrom(savedStateHandle)
        .palette

    private val _viewState: MutableStateFlow<PaletteDetailState> =
        MutableStateFlow(
            PaletteDetailState.CurrentPalette(selectedPalette)
        )
    val viewState: StateFlow<PaletteDetailState> = _viewState

    /*private fun fetchPaletteById(id: String) {
        paletteRepository.fetchPaletteById(id)
            .onEach { result ->
                _viewState.value = getViewStateForDetails(result)
            }
            .launchIn(viewModelScope)
    }

    private fun getViewStateForDetails(result: Result<Palette>): PaletteDetailState {
        return when (result) {
            is Result.Success -> {
                val data = result.data
                viewState.value.copy(
                    isLoading = false,
                    paletteColorList = data.colorList,
                    paletteName = data.name
                )
            }
            is Result.Error -> {
                viewState.value.copy(
                    isLoading = false,
                    errorMessage = UIText.StringText(result.error.message ?: "Palette not found")
                )
            }
        }
    }*/

    fun updateSelectedIndex(index: Int) {
        val palette = (viewState.value as PaletteDetailState.CurrentPalette).palette
        _viewState.value =
            PaletteDetailState.CurrentPalette(palette = palette, selectedIndex = index)
    }

    // TODO : This function is changing state but in UI it is not reflecting.
    fun deleteSelectedColor() {
        var selectedIndex = (viewState.value as PaletteDetailState.CurrentPalette).selectedIndex
        val currentPalette = (viewState.value as PaletteDetailState.CurrentPalette).palette
        val list = currentPalette.colorList
        list.removeAt(index = selectedIndex)
        if (selectedIndex == currentPalette.colorList.size) {
            selectedIndex -= 1
        }
        val newPalette = Palette(
            id = selectedPalette.id,
            name = selectedPalette.name,
            colorList = list,
            modifiedAt = System.currentTimeMillis()
        )
        _viewState.update {
            PaletteDetailState.CurrentPalette(newPalette, selectedIndex)
        }
        paletteRepository.updatePalette(newPalette = newPalette)
    }
}
