package com.akashk.palette.colorpicker

import androidx.activity.ComponentActivity
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import com.google.common.truth.Truth.assertThat
import org.junit.Rule
import org.junit.Test

class ColorPickerScreenContentTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @OptIn(ExperimentalMaterial3Api::class)
    @Test
    fun checkIfEverythingIsRenderedProperly() {
        val viewState = ColorPickerState.CurrentPalette()

        composeTestRule.setContent {
            ColorPickerScreenContent(viewState = viewState, pickColor = {})
        }

        colorPickerScreenRobot(composeTestRule) {
            cameraXComposableIsVisible()
            currentColorIsVisible()
            pickColorButtonIsVisible()
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Test
    fun checkIfPickColorInvoked() {
        val viewState = ColorPickerState.CurrentPalette()
        var isInvoked = false
        composeTestRule.setContent {
            ColorPickerScreenContent(viewState = viewState, pickColor = {
                isInvoked = true
            })
        }

        colorPickerScreenRobot(composeTestRule) {
            clickPickColorButton()
        }

        assertThat(isInvoked).isTrue()
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Test
    fun checkIfColorListItemsVisible() {
        val color = "#6750a4"
        val colorList = mutableListOf(color)
        val viewState = ColorPickerState.CurrentPalette(
            list = mutableListOf(color)
        )
        composeTestRule.setContent {
            ColorPickerScreenContent(viewState = viewState, pickColor = {})
        }
        colorPickerScreenRobot(composeTestRule) {
            colorListIsVisibleIfExists(colorList)
        }
    }
}
