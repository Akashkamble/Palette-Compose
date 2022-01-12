package com.akashk.palette.createnewpalette

import com.akashk.palette.core.ui.UIText

sealed class NewPaletteState(
    open val paletteName: String
) {

    /**
     * When user lands on screen the initially the edit text will have blank string.
     */
    object Initial : NewPaletteState("")

    /**
     *  When user is entering the name of palette
     *  and when edit text is active but has error.
     */
    data class Active(
        override val paletteName: String,
        val paletteNameError: UIText? = null
    ) : NewPaletteState(
        paletteName = paletteName
    )

    /**
     * When user has clicked the button pass palette name to ColorPickerScreen.
     */
    data class NavigateToColorPicker(
        override val paletteName: String
    ) : NewPaletteState(
        paletteName = paletteName
    )
}
