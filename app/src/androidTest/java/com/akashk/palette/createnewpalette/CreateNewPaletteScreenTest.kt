package com.akashk.palette.createnewpalette

import androidx.activity.ComponentActivity
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import com.akashk.palette.core.ui.UIText
import com.google.common.truth.Truth.assertThat
import org.junit.Rule
import org.junit.Test

class CreateNewPaletteScreenTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @OptIn(ExperimentalMaterial3Api::class)
    @Test
    fun checkIfNameTextFieldAndButtonIsVisible() {
        val viewState = NewPaletteState()
        composeTestRule.setContent {
            CreateNewPaletteContent(
                viewState = viewState,
                onTextChanged = {},
                onAddClick = {}
            )
        }

        composeTestRule
            .onNodeWithTag("palette_name_text_field")
            .assertIsDisplayed()
        composeTestRule
            .onNodeWithText("Continue")
            .assertIsDisplayed()
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Test
    fun checkIfOnTextChangedInvoked() {
        var isInvoked = false
        val viewState = NewPaletteState()
        composeTestRule.setContent {
            CreateNewPaletteContent(
                viewState = viewState,
                onTextChanged = {
                    isInvoked = true
                },
                onAddClick = {
                }
            )
        }
        composeTestRule
            .onNodeWithTag("palette_name_text_field")
            .performTextInput("Palette Name")

        assertThat(isInvoked).isTrue()
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Test
    fun checkIfOnAddClickInvoked() {
        var isInvoked = false
        val viewState = NewPaletteState()
        composeTestRule.setContent {
            CreateNewPaletteContent(
                viewState = viewState,
                onTextChanged = {
                },
                onAddClick = {
                    isInvoked = true
                }
            )
        }
        composeTestRule
            .onNodeWithText("Continue")
            .performClick()

        assertThat(isInvoked).isTrue()
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Test
    fun checkIfErrorMessageIsRendered() {
        val errorMessage = "Please enter valid name"
        val viewState = NewPaletteState(paletteNameError = UIText.StringText(errorMessage))
        composeTestRule.setContent {
            CreateNewPaletteContent(
                viewState = viewState,
                onTextChanged = {},
                onAddClick = {}
            )
        }
        composeTestRule
            .onNodeWithText(errorMessage)
            .assertIsDisplayed()
    }
}
