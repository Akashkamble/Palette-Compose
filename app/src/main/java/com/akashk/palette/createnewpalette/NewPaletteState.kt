package com.akashk.palette.createnewpalette

import com.akashk.palette.core.ui.UIText

/**
 * This class shows the current state of the [com.akashk.palette.createnewpalette.CreateNewPaletteScreen].
 * [paletteName] is the value entered in the TextField.
 * [paletteNameError] is the error message to display is entered palette name is invalid.
 */
data class NewPaletteState(
    val paletteName: String = "",
    val paletteNameError: UIText? = null,
)
