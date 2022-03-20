package com.akashk.palette.palettelist

import androidx.activity.ComponentActivity
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.akashk.palette.domain.data.Palette
import com.google.common.truth.Truth.assertThat
import org.junit.Rule
import org.junit.Test

class PaletteListContentTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    val palette = Palette(
        id = "1",
        name = "Palette 1",
        colorList = mutableListOf("#6750a4"),
        modifiedAt = System.currentTimeMillis()
    )

    @OptIn(ExperimentalMaterial3Api::class)
    @Test
    fun checkIfFABIsVisible() {
        composeTestRule.setContent {
            PaletteScreenContent(
                viewState = PaletteListViewState(
                    isLoading = false,
                    palettes = listOf(palette),
                    errorMessage = null
                ),
                onAddClick = { },
                onPaletteClick = {}
            )
        }

        composeTestRule
            .onNodeWithContentDescription("Add new palette")
            .assertIsDisplayed()
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Test
    fun checkIfCircularIndicatorIsVisible() {
        composeTestRule.setContent {
            PaletteScreenContent(
                viewState = PaletteListViewState(
                    isLoading = true,
                    palettes = emptyList(),
                    errorMessage = null
                ),
                onAddClick = { },
                onPaletteClick = {}
            )
        }

        composeTestRule
            .onNodeWithTag("loading palette list")
            .assertIsDisplayed()
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Test
    fun checkIfListWithPaletteIsVisible() {
        composeTestRule.setContent {
            PaletteScreenContent(
                viewState = PaletteListViewState(
                    isLoading = false,
                    palettes = listOf(palette),
                    errorMessage = null
                ),
                onAddClick = { },
                onPaletteClick = {}
            )
        }

        composeTestRule
            .onNodeWithTag("Palette_${palette.id}")
            .assertIsDisplayed()
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Test
    fun checkIfonAddClickIsInvoked() {
        var isInvoked = false
        composeTestRule.setContent {
            PaletteScreenContent(
                viewState = PaletteListViewState(
                    isLoading = false,
                    palettes = listOf(palette),
                    errorMessage = null
                ),
                onAddClick = {
                    isInvoked = true
                },
                onPaletteClick = {}
            )
        }

        composeTestRule
            .onNodeWithContentDescription("Add new palette")
            .performClick()

        assertThat(isInvoked).isTrue()
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Test
    fun checkIfonPaletteClickIsInvoked() {
        var isInvoked = false
        composeTestRule.setContent {
            PaletteScreenContent(
                viewState = PaletteListViewState(
                    isLoading = false,
                    palettes = listOf(palette),
                    errorMessage = null
                ),
                onAddClick = {
                    isInvoked = true
                },
                onPaletteClick = {
                    isInvoked = true
                }
            )
        }

        composeTestRule
            .onNodeWithTag("Palette_${palette.id}")
            .performClick()

        assertThat(isInvoked).isTrue()
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Test
    fun checkIfEmptyStateIsVisible() {
        composeTestRule.setContent {
            PaletteScreenContent(
                viewState = PaletteListViewState(
                    isLoading = false,
                    palettes = listOf(),
                    errorMessage = null
                ),
                onAddClick = {},
                onPaletteClick = {}
            )
        }

        composeTestRule
            .onNodeWithContentDescription("Add new palette")
            .assertIsDisplayed()
        composeTestRule
            .onNodeWithContentDescription("Empty List")
            .assertIsDisplayed()
        composeTestRule
            .onNodeWithText("Palette is not added yet.")
            .assertIsDisplayed()
        composeTestRule
            .onNodeWithText("Click on the + to add new Palette.")
            .assertIsDisplayed()
    }
}
