package com.akashk.palette.palettedetail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.akashk.palette.destinations.PaletteDetailScreenDestination
import com.akashk.palette.domain.data.Palette
import com.akashk.palette.domain.data.PaletteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PaletteDetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val paletteRepository: PaletteRepository,
    private val useCase: IDeleteColorUseCase,
) : ViewModel() {

    private var selectedPalette: Palette = PaletteDetailScreenDestination
        .argsFrom(savedStateHandle)
        .palette

    private val _viewState: MutableStateFlow<PaletteDetailState> =
        MutableStateFlow(
            PaletteDetailState.CurrentPalette(selectedPalette)
        )
    val viewState: StateFlow<PaletteDetailState> = _viewState

    fun fetchPaletteById(id: String): Palette {
        val palette = paletteRepository.fetchPaletteById(id)
        _viewState.value = PaletteDetailState.CurrentPalette(palette = palette)
        return palette
    }

    fun updateSelectedIndex(index: Int) {
        val palette = (viewState.value as PaletteDetailState.CurrentPalette).palette
        _viewState.value =
            PaletteDetailState.CurrentPalette(palette = palette, selectedIndex = index)
    }

    fun deleteSelectedColor() {
        viewModelScope.launch {
            val newState = async { useCase.invoke(viewState.value) }
            _viewState.update { newState.await() }
        }
    }

    fun deletePalette() {
        viewModelScope.launch {
            paletteRepository.deletePalette(selectedPalette)
            _viewState.update {
                PaletteDetailState.CloseDetailsScreen
            }
        }
    }
}
