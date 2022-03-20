package com.akashk.palette.colorpicker

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.akashk.palette.destinations.ColorPickerScreenDestination
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ColorPickerViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val useCase: IAddColorUseCase
) : ViewModel() {

    private val _viewState: MutableStateFlow<ColorPickerState> =
        MutableStateFlow(ColorPickerState.CurrentPalette())
    val viewState: StateFlow<ColorPickerState> = _viewState
    private var palette = ColorPickerScreenDestination.argsFrom(savedStateHandle).palette

    init {
        _viewState.value = ColorPickerState.CurrentPalette(list = palette.colorList)
    }

    fun pickColor(color: String) {
        viewModelScope.launch {
            val newState = async { useCase.invoke(viewState.value, palette, color) }
            _viewState.update { newState.await() }
        }
    }
}
