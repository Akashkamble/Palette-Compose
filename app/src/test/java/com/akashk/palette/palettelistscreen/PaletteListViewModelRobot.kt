package com.akashk.palette.palettelistscreen

import com.akashk.palette.core.Result
import com.akashk.palette.domain.data.Palette
import com.akashk.palette.fakes.FakePaletteRepository
import com.akashk.palette.palettelist.PaletteListViewModel
import com.akashk.palette.palettelist.PaletteListViewState
import com.google.common.truth.Truth.assertThat

class PaletteListViewModelRobot {
    private val fakePaletteRepository = FakePaletteRepository()
    private lateinit var viewModel: PaletteListViewModel

    fun buildViewModel() = apply {
        viewModel = PaletteListViewModel(
            fakePaletteRepository.mock
        )
    }

    fun mockAllPalettesResult(result: Result<List<Palette>>) = apply {
        fakePaletteRepository.mockPaletteListResult(result)
    }

    fun assertViewState(expectedViewState: PaletteListViewState) = apply {
        val actualViewState = viewModel.viewState.value
        assertThat(actualViewState).isEqualTo(expectedViewState)
    }
}
