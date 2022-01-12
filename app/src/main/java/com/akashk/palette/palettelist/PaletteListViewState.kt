package com.akashk.palette.palettelist

import com.akashk.palette.core.ui.UIText
import com.akashk.palette.domain.data.Palette

sealed class PaletteListViewState {
    /**
     * This indicates the list is being loaded from local database.
     */
    object Loading : PaletteListViewState()

    /**
     * This indicates the list is successfully loaded from local database.
     */
    data class Loaded(
        val palettes: List<Palette>,
    ) : PaletteListViewState()

    /**
     * This indicates the exception was thrown while loading the list from local database.
     */
    data class Error(
        val errorMessage: UIText,
    ) : PaletteListViewState()

    /**
     * This indicates the add new palette button is clicked
     * and user will be navigate to Add palette Screen.
     */
    object AddNewPalette : PaletteListViewState()
}
