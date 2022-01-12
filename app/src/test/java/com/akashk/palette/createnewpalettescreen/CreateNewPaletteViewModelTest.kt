package com.akashk.palette.createnewpalettescreen

import com.akashk.palette.CoroutinesTestRule
import com.akashk.palette.ThreadExceptionHandlerTestRule
import com.akashk.palette.core.ui.UIText
import com.akashk.palette.createnewpalette.NewPaletteState
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class CreateNewPaletteViewModelTest {

    private lateinit var testRobot: CreateNewPaletteViewModelRobot

    @get:Rule
    val coroutinesTestRule = CoroutinesTestRule()

    @get:Rule
    val threadExceptionHandlerTestRule = ThreadExceptionHandlerTestRule()

    @Before
    fun setUp() {
        testRobot = CreateNewPaletteViewModelRobot()
    }

    @Test
    fun enteringColorPickerScreenWithValidPaletteName(): Unit = runBlocking {
        val paletteName = "New Palette"

        val expectedViewStates = listOf(
            NewPaletteState.Initial,
            NewPaletteState.Active(paletteName = paletteName),
            NewPaletteState.NavigateToColorPicker(paletteName = paletteName)
        )

        testRobot
            .buildViewModel()
            .expectViewStates(
                action = {
                    enterName(paletteName)
                    onContinueClick(paletteName)
                },
                viewStates = expectedViewStates
            )
    }

    @Test
    fun enteringColorPickerScreenWithoutValidPaletteName(): Unit = runBlocking {
        val paletteName = ""
        val expectedErrorMessage = "Enter valid palette name"
        val expectedViewStates = listOf(
            NewPaletteState.Initial,
            NewPaletteState.Active(paletteName = paletteName),
            NewPaletteState.Active(
                paletteName = paletteName,
                paletteNameError = UIText.StringText(expectedErrorMessage)
            )
        )

        testRobot
            .buildViewModel()
            .expectViewStates(
                action = {
                    enterName(paletteName)
                    onContinueClick(paletteName)
                },
                viewStates = expectedViewStates
            )
    }
}
