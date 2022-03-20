package com.akashk.palette.colorpicker

sealed class ColorPickerState {
    data class CurrentPalette(val list: List<String> = mutableListOf()) : ColorPickerState()
}