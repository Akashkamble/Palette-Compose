package com.akashk.palette.palettedetail

import androidx.lifecycle.SavedStateHandle
import com.akashk.palette.domain.data.Palette
import com.akashk.palette.fakes.FakeDeleteColorUseCase
import com.akashk.palette.fakes.FakePaletteRepository
import com.google.common.truth.Truth.assertThat
import io.mockk.every
import io.mockk.mockk

class PaletteDetailsViewModelRobot {

    private val fakePaletteRepository = FakePaletteRepository()
    private val savedStateHandle = mockk<SavedStateHandle>(relaxed = true)
    private lateinit var viewModel: PaletteDetailsViewModel
    private val useCase = FakeDeleteColorUseCase()

    fun buildViewModel(palette: Palette) = apply {
        every { savedStateHandle.get<Palette>("palette") } returns palette
        viewModel = PaletteDetailsViewModel(
            savedStateHandle = savedStateHandle,
            paletteRepository = fakePaletteRepository.mock,
            useCase = useCase.mock
        )
    }

    fun fetchPaletteById() = apply {
        viewModel.fetchPaletteById("")
    }

    fun mockFetchPaletteById(palette: Palette) = apply {
        fakePaletteRepository.mockFetchPaletteById(palette)
    }

    fun updateIndex(index: Int) = apply {
        viewModel.updateSelectedIndex(index = index)
    }

    fun deleteColor() = apply {
        viewModel.deleteSelectedColor()
    }

    fun deletePalette() = apply {
        viewModel.deletePalette()
    }

    fun mockDeletePalette(palette: Palette) = apply {
        fakePaletteRepository.mockDeletePalette(palette)
    }

    fun mockDeleteColorUseCaseResult(viewState: PaletteDetailState) = apply {
        useCase.mockResultOfDeleteColorUseCase(viewState = viewState)
    }

    fun assertViewState(expectedViewState: PaletteDetailState) = apply {
        val actualViewState = viewModel.viewState.value
        assertThat(actualViewState).isEqualTo(expectedViewState)
    }
}
