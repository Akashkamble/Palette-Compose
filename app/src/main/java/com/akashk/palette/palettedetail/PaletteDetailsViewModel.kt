package com.akashk.palette.palettedetail

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.akashk.palette.destinations.PaletteDetailScreenDestination
import com.akashk.palette.domain.data.Palette
import com.akashk.palette.domain.data.PaletteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import com.akashk.palette.core.Result
import com.akashk.palette.core.ui.UIText
@HiltViewModel
class PaletteDetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val paletteRepository: PaletteRepository
) : ViewModel() {

    private var selectedPalette: Palette = PaletteDetailScreenDestination
        .argsFrom(savedStateHandle)
        .palette

    private val _viewState: MutableStateFlow<PaletteDetailState> =
        MutableStateFlow(
            PaletteDetailState.CurrentPalette(selectedPalette)
        )
    val viewState: StateFlow<PaletteDetailState> = _viewState

    fun fetchPaletteById(id : String) : Palette {
      val palette = paletteRepository.fetchPaletteById(id)
      _viewState.value =  PaletteDetailState.CurrentPalette(palette = palette)
      return palette
    }

    fun updateSelectedIndex(index: Int) {
        val palette = (viewState.value as PaletteDetailState.CurrentPalette).palette
        _viewState.value =
            PaletteDetailState.CurrentPalette(palette = palette, selectedIndex = index)
    }

    fun deleteSelectedColor() {
        var selectedIndex = (viewState.value as PaletteDetailState.CurrentPalette).selectedIndex
        val currentPalette = (viewState.value as PaletteDetailState.CurrentPalette).palette
        val list = currentPalette.colorList
        list.removeAt(index = selectedIndex)
        if (selectedIndex == currentPalette.colorList.size) {
            selectedIndex -= 1
        }
        val newPalette = Palette(
            id = selectedPalette.id,
            name = selectedPalette.name,
            colorList = list,
            modifiedAt = System.currentTimeMillis()
        )
        _viewState.update {
            PaletteDetailState.CurrentPalette(newPalette, selectedIndex)
        }
        paletteRepository.updatePalette(newPalette = newPalette)
    }
}

