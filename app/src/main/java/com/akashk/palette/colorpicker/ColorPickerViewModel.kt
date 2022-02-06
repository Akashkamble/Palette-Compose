package com.akashk.palette.colorpicker

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.akashk.palette.destinations.ColorPickerScreenDestination
import com.akashk.palette.domain.data.PaletteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ColorPickerViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    val paletteRepository: PaletteRepository,
) : ViewModel() {

    private val _viewState: MutableStateFlow<ColorPickerState> =
        MutableStateFlow(ColorPickerState())
    val viewState: StateFlow<ColorPickerState> = _viewState
    private var palette = ColorPickerScreenDestination.argsFrom(savedStateHandle).palette

    init {
        _viewState.value = viewState.value.copy(paletteColorList = palette.colorList)
    }

    fun pickColor(color: String) {
        val list = viewState.value.paletteColorList
        if (list.isEmpty()) {
            list.add(color)
            _viewState.value = viewState.value.copy(paletteColorList = list)
            viewModelScope.launch {
                paletteRepository.addPalette(
                    palette = palette.copy(
                        colorList = list,
                        modifiedAt = System.currentTimeMillis()
                    )
                )
            }
        } else {
            list.add(color)
            _viewState.value = viewState.value.copy(paletteColorList = list)
            viewModelScope.launch {
                paletteRepository.updatePalette(
                    palette = palette.copy(
                        colorList = list,
                        modifiedAt = System.currentTimeMillis()
                    )
                )
            }
        }
    }
}

data class ColorPickerState(
    val paletteColorList: MutableList<String> = mutableListOf()
)
