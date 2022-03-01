package com.akashk.palette.palettedetail

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.akashk.palette.destinations.PaletteDetailScreenDestination
import com.akashk.palette.domain.data.Palette
import com.akashk.palette.domain.data.PaletteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class PaletteDetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val paletteRepository: PaletteRepository
) : ViewModel() {

    private val _viewState: MutableStateFlow<PaletteDetailsState> =
        MutableStateFlow(
            PaletteDetailsState(
                PaletteDetailScreenDestination.argsFrom(savedStateHandle).palette
            )
        )
    val viewState: StateFlow<PaletteDetailsState> = _viewState

    fun updateSelectedIndex(index: Int) {
        _viewState.value = viewState.value.copy(selectedIndex = index)
    }

    // TODO : This function is changing state but in UI it is not reflecting.
    fun deleteSelectedColor() {
        Log.d("Test", viewState.value.palette.toString())
        val index = viewState.value.selectedIndex
        val list = viewState.value.palette.colorList
        list.removeAt(index = index)
        _viewState.value = viewState.value.copy(
            palette = viewState.value.palette.copy(colorList = list),
            selectedIndex = viewState.value.selectedIndex
        )
        Log.d("Test1", viewState.value.palette.toString())
        paletteRepository.updatePalette(newPalette = viewState.value.palette)
    }
}

data class PaletteDetailsState(
    val palette: Palette,
    val selectedIndex: Int = 0
)
