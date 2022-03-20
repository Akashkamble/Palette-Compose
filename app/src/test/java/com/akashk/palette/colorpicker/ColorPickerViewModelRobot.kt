package com.akashk.palette.colorpicker

import androidx.lifecycle.SavedStateHandle
import com.akashk.palette.domain.data.Palette
import com.akashk.palette.fakes.FakeAddColorUseCase
import com.google.common.truth.Truth
import io.mockk.every
import io.mockk.mockk

class ColorPickerViewModelRobot {

    private val savedStateHandle = mockk<SavedStateHandle>(relaxed = true)
    private lateinit var viewModel: ColorPickerViewModel
    private val useCase = FakeAddColorUseCase()

    fun buildViewModel(palette: Palette) = apply {
        every { savedStateHandle.get<Palette>("palette") } returns palette
        viewModel = ColorPickerViewModel(
            savedStateHandle = savedStateHandle,
            useCase = useCase.mock
        )
    }

    fun addColor(color: String) = apply {
        viewModel.pickColor(color)
    }

    fun mockAddColorUseCaseResult(result: ColorPickerState) = apply {
        useCase.mockResultOfAddColorUseCase(result)
    }

    fun assertViewState(expectedViewState: ColorPickerState) = apply {
        val actualViewState = viewModel.viewState.value
        Truth.assertThat(actualViewState).isEqualTo(expectedViewState)
    }
}
