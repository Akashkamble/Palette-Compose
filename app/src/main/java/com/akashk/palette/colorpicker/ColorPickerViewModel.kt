package com.akashk.palette.colorpicker

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.akashk.palette.destinations.ColorPickerScreenDestination
import com.akashk.palette.domain.data.Palette
import com.akashk.palette.domain.data.PaletteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class ColorPickerViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    val paletteRepository: PaletteRepository,
) : ViewModel() {
    val palette = ColorPickerScreenDestination.argsFrom(savedStateHandle).palette

    private val _viewState: MutableStateFlow<ColorPickerState> =
        MutableStateFlow(ColorPickerState(palette = palette))
    val viewState: StateFlow<ColorPickerState> = _viewState

    fun pickColor(color: String) {
        val list = viewState.value.palette.colorList
        list.add(color)
        _viewState.value = viewState.value.copy(palette = palette.copy(colorList = list))
        /*if (list.isEmpty()) {
            list.add(color)
            _viewState.value = viewState.value.copy(palette = palette.copy(colorList = list))
            viewModelScope.launch {
                paletteRepository.addPalette(viewState.value.palette)
            }
        } else {
            list.add(color)
            _viewState.value = _viewState.value.copy(palette = palette.copy(colorList = list))
        }*/

    }
}

data class ColorPickerState(
    val palette: Palette
)
