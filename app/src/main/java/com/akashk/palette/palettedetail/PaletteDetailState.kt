package com.akashk.palette.palettedetail

import com.akashk.palette.core.ui.UIText

data class PaletteDetailState(
    val isLoading: Boolean = true,
    val paletteColorList: MutableList<String> = mutableListOf(),
    val paletteName: String = "",
    val selectedIndex: Int = 0,
    val errorMessage: UIText? = null
)
