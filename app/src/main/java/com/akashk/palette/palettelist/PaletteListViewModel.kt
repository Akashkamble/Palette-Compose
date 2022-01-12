package com.akashk.palette.palettelist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.akashk.palette.core.Result
import com.akashk.palette.core.ui.UIText
import com.akashk.palette.domain.data.Palette
import com.akashk.palette.domain.data.PaletteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class PaletteListViewModel @Inject constructor(
    paletteRepository: PaletteRepository
) : ViewModel() {
    private val _viewState: MutableStateFlow<PaletteListViewState> =
        MutableStateFlow(PaletteListViewState.Loading)

    val viewState: StateFlow<PaletteListViewState> = _viewState

    init {
        paletteRepository.fetchAllPalettes()
            .onEach { result ->
                _viewState.value = getPaletteListViewState(result)
            }
            .launchIn(viewModelScope)
    }

    fun addNewPalette() {
        _viewState.value = PaletteListViewState.AddNewPalette
    }

    private fun getPaletteListViewState(result: Result<List<Palette>>): PaletteListViewState {
        return when (result) {
            is Result.Error -> {
                PaletteListViewState.Error(
                    UIText.StringText(
                        result.error.localizedMessage ?: "Something went wrong"
                    )
                )
            }
            is Result.Success -> {
                PaletteListViewState.Loaded(result.data)
            }
        }
    }
}
