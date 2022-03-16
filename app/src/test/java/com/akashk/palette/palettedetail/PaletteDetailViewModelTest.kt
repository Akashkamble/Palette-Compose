package com.akashk.palette.palettedetail

import com.akashk.palette.CoroutinesTestRule
import com.akashk.palette.domain.data.Palette
import org.junit.Rule
import org.junit.Test

class PaletteDetailViewModelTest {

    private val testRobot = PaletteDetailsViewModelRobot()

    @get:Rule
    val coroutinesTestRule = CoroutinesTestRule()

    @Test
    fun checkIfFetchPaletteByIdReturnCorrectViewState() {
        val palette = Palette(
            "1",
            "New Palette",
            colorList = mutableListOf("#000000"),
            modifiedAt = 1L
        )

        testRobot
            .mockFetchPaletteById(palette = palette)
            .buildViewModel(palette = palette)
            .fetchPaletteById()
            .assertViewState(
                expectedViewState = PaletteDetailState.CurrentPalette(palette = palette)
            )
    }

    @Test
    fun checkUpdateIndexReturnsCorrectViewState() {
        val palette = Palette(
            "1",
            "New Palette",
            colorList = mutableListOf("#000000", "#000000", "#000000"),
            modifiedAt = 1L
        )
        val selectedIndex = 2
        testRobot
            .buildViewModel(palette = palette)
            .updateIndex(selectedIndex)
            .assertViewState(
                expectedViewState = PaletteDetailState.CurrentPalette(
                    palette = palette,
                    selectedIndex = selectedIndex
                )
            )
    }

    @Test
    fun checkIfGettingCorrectViewStateAfterDeleteColor() {
        val palette = Palette(
            "1",
            "New Palette",
            colorList = mutableListOf("#000000", "#000000", "#000000"),
            modifiedAt = 1L
        )
        val expectedViewState = PaletteDetailState.CurrentPalette(
            palette = palette.copy(
                colorList = mutableListOf("#000000", "#000000"),
            ),
            selectedIndex = 0
        )
        testRobot
            .mockDeleteColorUseCaseResult(expectedViewState)
            .buildViewModel(palette = palette)
            .deleteColor()
            .assertViewState(
                expectedViewState = expectedViewState
            )
    }

    @Test
    fun checkIfAfterDeletingPaletteDetailsScreenCloses() {
        val palette = Palette(
            "1",
            "New Palette",
            colorList = mutableListOf("#000000", "#000000", "#000000"),
            modifiedAt = 1L
        )
        val expectedViewState = PaletteDetailState.CloseDetailsScreen

        testRobot
            .mockDeletePalette(palette = palette)
            .buildViewModel(palette = palette)
            .deletePalette()
            .assertViewState(
                expectedViewState = expectedViewState
            )
    }
}
