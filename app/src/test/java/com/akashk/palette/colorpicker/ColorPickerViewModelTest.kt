package com.akashk.palette.colorpicker

import com.akashk.palette.CoroutinesTestRule
import com.akashk.palette.domain.data.Palette
import com.akashk.palette.palettedetail.PaletteDetailState
import org.junit.Rule
import org.junit.Test

class ColorPickerViewModelTest {

    val testRobot = ColorPickerViewModelRobot()

    @get:Rule
    val coroutinesTestRule = CoroutinesTestRule()

    @Test
    fun checkIfDeleteColorReturnCorrectViewState() {
        val newColor = "#000000"
        val palette = Palette(
            "1",
            "New Palette",
            colorList = mutableListOf(""),
            modifiedAt = 1L
        )

        val expectedViewState = ColorPickerState.CurrentPalette(
            list = mutableListOf(newColor)
        )
        testRobot
            .mockAddColorUseCaseResult(expectedViewState)
            .buildViewModel(palette = palette)
            .addColor(newColor)
            .assertViewState(
                expectedViewState = expectedViewState
            )
    }
}