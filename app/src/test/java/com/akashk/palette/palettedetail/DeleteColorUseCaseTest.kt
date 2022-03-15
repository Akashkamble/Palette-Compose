package com.akashk.palette.palettedetail

import com.akashk.palette.core.Result
import com.akashk.palette.core.ui.UIText
import com.akashk.palette.domain.data.Palette
import com.akashk.palette.domain.data.PaletteRepository
import com.google.common.truth.Truth.assertThat
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class DeleteColorUseCaseTest {

    val repo = mockk<PaletteRepository>()
    private lateinit var useCase: DeleteColorUseCase

    @Before
    fun setUp() {
        useCase = DeleteColorUseCase(repo)
    }

    @Test
    fun checkIfWholePaletteIsDeleted() = runBlocking {
        val palette = Palette(
            "1",
            name = "New Palette",
            colorList = mutableListOf("#6750a4"),
            modifiedAt = System.currentTimeMillis()
        )
        val viewState = PaletteDetailState.CurrentPalette(
            palette = palette,
            selectedIndex = 0
        )
        val expectedState = PaletteDetailState.CloseDetailsScreen
        coEvery { repo.deletePalette(any()) } returns Result.Success(Unit)
        val newState = useCase.invoke(viewState = viewState)
        coVerify { repo.deletePalette(palette = palette) }
        assertThat(newState).isEqualTo(expectedState)
    }

    @Test
    fun checkIfLastColorDeletedSelectedIndexIsReducedByOne() = runBlocking {
        val palette = Palette(
            "1",
            name = "New Palette",
            colorList = mutableListOf("#6750a4", "#000000"),
            modifiedAt = System.currentTimeMillis()
        )
        val viewState = PaletteDetailState.CurrentPalette(
            palette = palette,
            selectedIndex = 1
        )
        val expectedPalette = Palette(
            "1",
            name = "New Palette",
            colorList = mutableListOf("#6750a4"),
            modifiedAt = System.currentTimeMillis()
        )
        val expectedState = PaletteDetailState.CurrentPalette(
            palette = expectedPalette,
            selectedIndex = 0
        )
        coEvery { repo.updatePalette(any()) } returns Result.Success(Unit)
        val newState = useCase.invoke(viewState = viewState)
        assertThat((newState as PaletteDetailState.CurrentPalette).palette.colorList.size).isEqualTo(expectedState.palette.colorList.size)
        assertThat(newState.selectedIndex).isEqualTo(expectedState.selectedIndex)
    }

    @Test
    fun checkIfGetErrorStateWhenViewStateIsNotCurrentPaletteState() = runBlocking {
        val viewState = PaletteDetailState.IsLoading(flag = true)
        val expectedViewState = PaletteDetailState.ErrorState(UIText.StringText("Something went wrong"))
        val newState = useCase.invoke(viewState = viewState)
        assertThat(newState).isEqualTo(expectedViewState)
    }
}
