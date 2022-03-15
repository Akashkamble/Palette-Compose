package com.akashk.palette.palettedetail

import com.akashk.palette.core.ui.UIText
import com.akashk.palette.domain.data.Palette
import com.akashk.palette.domain.data.PaletteRepository

class DeleteColorUseCase(private val repo: PaletteRepository) {
    suspend operator fun invoke(viewState: PaletteDetailState): PaletteDetailState {
        if (viewState is PaletteDetailState.CurrentPalette) {
            var selectedIndex = viewState.selectedIndex
            val currentPalette = viewState.palette
            val list = currentPalette.colorList

            if (list.size == 1) {
                /*
                 * Now we have only one color so if we want to delete the last color
                 * we can delete the whole palette.
                 */
                repo.deletePalette(currentPalette)
                return PaletteDetailState.CloseDetailsScreen
            } else {
                list.removeAt(index = selectedIndex)
                /*
                 * If we want to delete last color then after deleting that color selected
                 * color should be the last color.
                 */
                if (selectedIndex == currentPalette.colorList.size) {
                    selectedIndex -= 1
                }
                val newPalette = Palette(
                    id = currentPalette.id,
                    name = currentPalette.name,
                    colorList = list,
                    modifiedAt = System.currentTimeMillis()
                )
                repo.updatePalette(newPalette)
                return PaletteDetailState.CurrentPalette(newPalette, selectedIndex)
            }
        } else {
            return PaletteDetailState.ErrorState(UIText.StringText("Something went wrong"))
        }
    }
}
