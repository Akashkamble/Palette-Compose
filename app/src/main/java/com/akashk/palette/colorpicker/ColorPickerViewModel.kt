package com.akashk.palette.colorpicker

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class ColorPickerViewModel @Inject constructor() : ViewModel() {
    private val _viewState: MutableStateFlow<ColorPickerState> =
        MutableStateFlow(ColorPickerState())
    val viewState: StateFlow<ColorPickerState> = _viewState

    fun pickColor(color: String) {
        val list = viewState.value.paletteColorList
        list.add(color)
        _viewState.value = _viewState.value.copy(paletteColorList = list)
    }
}

data class ColorPickerState(
    val paletteColorList: MutableList<String> = mutableListOf()
)
