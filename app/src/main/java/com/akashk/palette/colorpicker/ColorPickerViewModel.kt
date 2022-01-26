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
        _viewState.value.palettColorList.add(color)
        _viewState.value = _viewState.value.copy(palettColorList = _viewState.value.palettColorList)
    }
}

data class ColorPickerState(
    val palettColorList: MutableList<String> = mutableListOf()
)
