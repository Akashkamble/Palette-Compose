package com.akashk.palette.colorpicker

import com.akashk.palette.domain.data.Palette

interface IAddColorUseCase {
    suspend operator fun invoke(
        viewState: ColorPickerState,
        palette: Palette,
        color: String
    ): ColorPickerState
}
