package com.akashk.palette.palettelistscreen

import com.akashk.palette.CoroutinesTestRule
import com.akashk.palette.core.Result
import com.akashk.palette.core.ui.UIText
import com.akashk.palette.domain.data.Palette
import com.akashk.palette.palettelist.PaletteListViewState
import org.junit.Rule
import org.junit.Test

class PaletteListViewModelTest {
    private val testRobot = PaletteListViewModelRobot()

    @get:Rule
    val coroutinesTestRule = CoroutinesTestRule()

    @Test
    fun fetchPaletteListSuccessfully() {
        val palette = Palette(
            id = 1,
            name = "Palette 1",
            colorList = listOf("#6750a4", "#4534ff", "#0004fc", "#6750d8")
        )

        val expectedList = listOf(palette)
        val paletteListResult = Result.Success(expectedList)

        testRobot
            .mockAllPalettesResult(paletteListResult)
            .buildViewModel()
            .assertViewState(
                expectedViewState = PaletteListViewState.Loaded(expectedList)
            )
    }

    @Test
    fun fetchPaletteListWithError() {
        val errorMessage = "Something went wrong"
        val expectedResult = Result.Error(Throwable())

        testRobot
            .mockAllPalettesResult(expectedResult)
            .buildViewModel()
            .assertViewState(
                expectedViewState = PaletteListViewState.Error(UIText.StringText(errorMessage))
            )
    }
}
