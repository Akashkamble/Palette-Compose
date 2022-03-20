package com.akashk.palette.colorpicker

import com.akashk.palette.domain.data.Palette
import com.akashk.palette.domain.data.PaletteRepository
import javax.inject.Inject

/**
 * This use case simplifies the logic of adding new color.
 * It takes current viewState as Input and returns new viewState after adding new color.
 */
class AddColorUseCase @Inject constructor(
    private val repo: PaletteRepository
) : IAddColorUseCase {
    override suspend fun invoke(
        viewState: ColorPickerState,
        palette: Palette,
        color: String
    ): ColorPickerState {
        val list = (viewState as ColorPickerState.CurrentPalette).list.toMutableList()
        if (list.isEmpty()) {
            val newList = newList(list, color)
            val newPalette = palette.copy(
                colorList = newList.toMutableList(),
                modifiedAt = System.currentTimeMillis()
            )
            repo.addPalette(
                palette = newPalette
            )
            return ColorPickerState.CurrentPalette(list = newList)
        } else {
            val newList = newList(list, color)
            val newPalette = palette.copy(
                colorList = newList.toMutableList(),
                modifiedAt = System.currentTimeMillis()
            )
            repo.updatePalette(
                newPalette = newPalette
            )
            return ColorPickerState.CurrentPalette(list = newList)
        }
    }

    private fun newList(list: MutableList<String>, color: String): List<String> {
        list.add(color)
        return list
    }
}
