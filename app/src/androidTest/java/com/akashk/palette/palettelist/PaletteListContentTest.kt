package com.akashk.palette.palettelist

import androidx.activity.ComponentActivity
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import com.akashk.palette.domain.data.Palette
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
                onAddClick = { }
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
                onAddClick = { }
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
                onAddClick = { }
            )
        }

        composeTestRule
            .onNodeWithTag("Palette_${palette.id}")
            .assertIsDisplayed()
    }
}
