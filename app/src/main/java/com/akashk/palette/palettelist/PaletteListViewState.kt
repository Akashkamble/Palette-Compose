package com.akashk.palette.palettelist

import com.akashk.palette.core.ui.UIText
import com.akashk.palette.domain.data.Palette

/**
 * PaletteListViewState shows the state of the Palette List Screen.
 * [isLoading] shows the circular progress bar on the screen when stored data is being loaded.
 * [palettes] these are the palettes displayed in the form of List on the screen.
 * [errorMessage] shown on the screen when we get error while fetching the stored data.
 */
data class PaletteListViewState(
    val isLoading: Boolean = true,
    val palettes: List<Palette> = emptyList(),
    val errorMessage: UIText? = null
)
