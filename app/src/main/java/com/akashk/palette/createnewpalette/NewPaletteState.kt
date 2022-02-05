package com.akashk.palette.createnewpalette

import com.akashk.palette.core.ui.UIText

data class NewPaletteState(
    val paletteName: String = "",
    val paletteNameError: UIText? = null,
)
