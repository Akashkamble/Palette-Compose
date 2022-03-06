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
    private val paletteRepository: PaletteRepository,
) : ViewModel() {

    private val _viewState: MutableStateFlow<ColorPickerState> =
        MutableStateFlow(ColorPickerState.CurrentPalette())
    val viewState: StateFlow<ColorPickerState> = _viewState
    private var palette = ColorPickerScreenDestination.argsFrom(savedStateHandle).palette

    init {
        _viewState.value = ColorPickerState.CurrentPalette(list = palette.colorList)
    }

    fun pickColor(color: String) {
        val list = (viewState.value as ColorPickerState.CurrentPalette).list
        if (list.isEmpty()) {
            _viewState.value = ColorPickerState.CurrentPalette(list = newList(list, color))
             val newPalette = palette.copy(
                 colorList = (viewState.value as ColorPickerState.CurrentPalette).list.toMutableList(),
                 modifiedAt = System.currentTimeMillis()
             )
            viewModelScope.launch {
                paletteRepository.addPalette(
                    palette = newPalette
                )
            }
        } else {
            _viewState.value = ColorPickerState.CurrentPalette(list = newList(list, color))
            val newPalette = palette.copy(
                colorList = (viewState.value as ColorPickerState.CurrentPalette).list.toMutableList(),
                modifiedAt = System.currentTimeMillis()
            )
            viewModelScope.launch {
                paletteRepository.updatePalette(
                    newPalette = newPalette
                )
            }
        }
    }

    private fun newList(list: List<String>, color: String) : List<String> {
        val newList : MutableList<String> = mutableListOf()
        newList.addAll(0, list)
        newList.add(color)
        return newList
    }
}

sealed class ColorPickerState{
  data class CurrentPalette(val list: List<String> = mutableListOf()) : ColorPickerState()
}