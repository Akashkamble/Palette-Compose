package com.akashk.palette.palettedetail

import com.akashk.palette.core.ui.UIText
import com.akashk.palette.domain.data.Palette

sealed class PaletteDetailState(open val paletteName: String) {
    data class IsLoading(val flag: Boolean) : PaletteDetailState("")
    data class CurrentPalette(
        val palette: Palette,
        val selectedIndex: Int = 0,
    ) : PaletteDetailState(paletteName = palette.name)
    data class ErrorState(val error: UIText) : PaletteDetailState("")
    object CloseDetailsScreen : PaletteDetailState("")
}
