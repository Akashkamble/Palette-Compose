package com.akashk.palette.colorpicker

import com.akashk.palette.domain.data.Palette
import com.akashk.palette.domain.data.PaletteRepository
import javax.inject.Inject

class AddColorUseCase @Inject constructor(
    private val repo : PaletteRepository
) : IAddColorUseCase {
    override suspend fun invoke(
        viewState: ColorPickerState,
        palette: Palette,
        color : String
    ): ColorPickerState {
        val list = (viewState as ColorPickerState.CurrentPalette).list
        if(list.isEmpty()){
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

    private fun newList(list: List<String>, color: String): List<String> {
        val newList: MutableList<String> = mutableListOf()
        newList.addAll(0, list)
        newList.add(color)
        return newList
    }
}