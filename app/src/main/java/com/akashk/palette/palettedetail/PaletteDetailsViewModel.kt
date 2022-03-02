package com.akashk.palette.palettedetail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.akashk.palette.core.Result
import com.akashk.palette.core.ui.UIText
import com.akashk.palette.destinations.PaletteDetailScreenDestination
import com.akashk.palette.domain.data.Palette
import com.akashk.palette.domain.data.PaletteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

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
            PaletteDetailState()
        )
    val viewState: StateFlow<PaletteDetailState> = _viewState

    init {
        val randomIndex = (0 until selectedPalette.colorList.size).random()
        updateSelectedIndex(randomIndex)
        fetchPaletteById(selectedPalette.id)
    }

    private fun fetchPaletteById(id: String) {
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
    }

    fun updateSelectedIndex(index: Int) {
        _viewState.value = viewState.value.copy(selectedIndex = index)
    }

    // TODO : This function is changing state but in UI it is not reflecting.
    fun deleteSelectedColor() {
        viewModelScope.launch {
            val index = viewState.value.selectedIndex
            val list = viewState.value.paletteColorList
            list.removeAt(index = index)
            /*
             *  Without changing selected index viewstate is not updating
             *  hence I have to create new state with index 0 (i.e default value)
             *  and then again update the state with selected state.
             *  If user has selected 0th index then I have to update selected index as random index
             *  and after delay again update selected index as 0.
             *  Also, without delay updateSelectedIndex does not update viewstate.
             *
             *  If you're reading this please fix this and create pull request 😂😂😂😂.
             */
            /*------------Bad Code starts here-----------------*/
            val newState = PaletteDetailState(
                isLoading = false,
                paletteColorList = list,
                paletteName = selectedPalette.name
            )
            _viewState.value = newState
            delay(10)
            if (index == 0) {
                val randomIndex = (0 until list.size).random()
                updateSelectedIndex(index = randomIndex)
                delay(10)
            }
            updateSelectedIndex(index = index)
            /*------------Bad Code ends here-----------------*/

            val palette = selectedPalette.copy(colorList = list)
            paletteRepository.updatePalette(newPalette = palette)
        }
    }
}
