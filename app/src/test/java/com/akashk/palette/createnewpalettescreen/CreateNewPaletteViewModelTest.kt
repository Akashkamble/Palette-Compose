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
        val initial = NewPaletteState()
        val validNameState = initial.copy(paletteName = paletteName)
        val expectedViewStates = listOf(
            initial,
            validNameState
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
        val initial = NewPaletteState()
        val stateWithName = initial.copy(paletteName = paletteName)
        val errorState = stateWithName.copy(paletteName = paletteName, paletteNameError = UIText.StringText(expectedErrorMessage))
        val expectedViewStates = listOf(
            stateWithName,
            errorState
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
