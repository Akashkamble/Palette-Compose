package com.akashk.palette.colorpicker

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.ComposeTestRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick

class ColorPickerScreenRobot(
    private val composeTestRule: ComposeTestRule
) {
    private val cameraXComposable = composeTestRule
        .onNodeWithTag("camerax_tag")
    private val currentColor = composeTestRule
        .onNodeWithTag("current_color_tag")
    private val pickColorButton = composeTestRule
        .onNodeWithTag("pick_color_tag")

    fun cameraXComposableIsVisible() {
        cameraXComposable.assertIsDisplayed()
    }
    fun currentColorIsVisible() {
        currentColor.assertIsDisplayed()
    }
    fun pickColorButtonIsVisible() {
        pickColorButton.assertIsDisplayed()
    }

    fun clickPickColorButton() {
        pickColorButton.performClick()
    }

    fun colorListIsVisibleIfExists(colorList: List<String>) {
        colorList.forEach {
            composeTestRule
                .onNodeWithTag("color_tag_$it")
                .assertIsDisplayed()
        }
    }
}

fun colorPickerScreenRobot(composeTestRule: ComposeTestRule, block: ColorPickerScreenRobot.() -> Unit) {
    ColorPickerScreenRobot(composeTestRule).apply(block)
}
