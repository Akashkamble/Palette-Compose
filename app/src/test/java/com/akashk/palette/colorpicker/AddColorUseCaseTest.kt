package com.akashk.palette.colorpicker

import com.akashk.palette.core.Result
import com.akashk.palette.domain.data.Palette
import com.akashk.palette.domain.data.PaletteRepository
import com.akashk.palette.palettedetail.DeleteColorUseCase
import com.google.common.truth.Truth.assertThat
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class AddColorUseCaseTest {
    val repo = mockk<PaletteRepository>()
    private lateinit var useCase: AddColorUseCase

    @Before
    fun setUp() {
        useCase = AddColorUseCase(repo)
    }

    @Test
    fun checkIfNewPaletteIsAdded() = runBlocking {
        val newColor = "#000000"
        val palette = Palette(
            "1",
            name = "New Palette",
            colorList = mutableListOf(),
            modifiedAt = System.currentTimeMillis()
        )
        val viewState = ColorPickerState.CurrentPalette(
            palette.colorList
        )
        val expectedState = ColorPickerState.CurrentPalette(listOf(newColor))
        coEvery { repo.addPalette(any()) } returns Result.Success(Unit)
        val newState = useCase.invoke(viewState, palette, newColor)
        coVerify { repo.addPalette(any()) }
        assertThat((newState as ColorPickerState.CurrentPalette).list.size).isEqualTo(expectedState.list.size)
    }

    @Test
    fun checkIfExistingPaletteIsUpdated() = runBlocking {
        val newColor = "#000000"
        val palette = Palette(
            "1",
            name = "New Palette",
            colorList = mutableListOf("#FFFFFF"),
            modifiedAt = System.currentTimeMillis()
        )
        val viewState = ColorPickerState.CurrentPalette(
            palette.colorList
        )
        val expectedState = ColorPickerState.CurrentPalette(listOf("#FFFFFF",newColor))
        coEvery { repo.updatePalette(any()) } returns Result.Success(Unit)
        val newState = useCase.invoke(viewState, palette, newColor)
        coVerify { repo.updatePalette(any()) }
        assertThat((newState as ColorPickerState.CurrentPalette).list.size).isEqualTo(expectedState.list.size)
    }
}