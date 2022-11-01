package com.akashk.palette.createnewpalette

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.ComposeTestRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput

class CreateNewPaletteRobot(
    private val composeTestRule: ComposeTestRule
) {
    private val continueButton = composeTestRule
        .onNodeWithText("Continue")

    private val textField = composeTestRule
        .onNodeWithTag("palette_name_text_field")

    fun everythingExceptErrorMessageIsVisible() {
        textField.assertIsDisplayed()
        continueButton.assertIsDisplayed()
    }

    fun clickContinueButton() {
        continueButton.performClick()
    }

    fun enterPaletteName(paletteName: String) {
        textField.performTextInput(paletteName)
    }

    fun errorMessageIsVisible(errorMessage: String) {
        composeTestRule
            .onNodeWithText(errorMessage)
    }
}

fun createNewPaletteRobot(composeTestRule: ComposeTestRule, block: CreateNewPaletteRobot.() -> Unit) {
    CreateNewPaletteRobot(composeTestRule).apply(block)
}
