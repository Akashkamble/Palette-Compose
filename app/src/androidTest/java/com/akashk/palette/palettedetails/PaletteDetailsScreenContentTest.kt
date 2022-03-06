package com.akashk.palette.palettedetails

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.akashk.palette.domain.data.Palette
import com.akashk.palette.palettedetail.PaletteDetailState
import com.akashk.palette.palettedetail.PaletteDetailsContent
import com.google.common.truth.Truth.assertThat
import org.junit.Rule
import org.junit.Test

class PaletteDetailsScreenContentTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    val palette = Palette(
        id = "1",
        name = "Palette 1",
        colorList = mutableListOf("#6750a4"),
        modifiedAt = System.currentTimeMillis()
    )

    @Test
    fun checkIfEveryThingIsRendered() {
        val viewState = PaletteDetailState.CurrentPalette(palette = palette)
        composeTestRule.setContent {
            PaletteDetailsContent(
                viewState = viewState,
                onDeletePalette = {},
                onSelectedColorIndex = {},
                onAddColor = {},
                onDeleteColor = {},
                onRenamePalette = {}
            )
        }
        /*----Palette ToolBar Nodes-----*/
        composeTestRule
            .onNodeWithText(palette.name)
            .assertIsDisplayed()
        composeTestRule
            .onNodeWithContentDescription("Delete whole palette")
            .assertIsDisplayed()

        /*----Palette BottomNav Nodes-----*/
        composeTestRule
            .onNodeWithTag("delete_color_tag")
            .assertIsDisplayed()
        composeTestRule
            .onNodeWithTag("add_color_tag")
            .assertIsDisplayed()
        composeTestRule
            .onNodeWithTag("rename_palette_tag")
            .assertIsDisplayed()

        /*----Palette ColorBox Nodes-----*/
        composeTestRule
            .onNodeWithTag("color_box_tag")
            .assertIsDisplayed()
        composeTestRule
            .onNodeWithText("#6750a4")
            .assertIsDisplayed()

        /*----Palette ColorGrid Nodes-----*/
        composeTestRule
            .onNodeWithTag("color_item_${palette.colorList[0]}")
    }

    @Test
    fun checkIfonDeletePaletteInvoked() {
        var isInvoked = false
        val viewState = PaletteDetailState.CurrentPalette(palette = palette)
        composeTestRule.setContent {
            PaletteDetailsContent(
                viewState = viewState,
                onDeletePalette = {
                    isInvoked = true
                },
                onSelectedColorIndex = {},
                onAddColor = {},
                onDeleteColor = {},
                onRenamePalette = {}
            )
        }

        composeTestRule
            .onNodeWithContentDescription("Delete whole palette")
            .performClick()

        assertThat(isInvoked).isTrue()
    }

    @Test
    fun checkIfonSelectedColorIndexInvoked() {
        var isInvoked = false
        val viewState = PaletteDetailState.CurrentPalette(palette = palette)
        composeTestRule.setContent {
            PaletteDetailsContent(
                viewState = viewState,
                onDeletePalette = {},
                onSelectedColorIndex = {
                    isInvoked = true
                },
                onAddColor = {},
                onDeleteColor = {},
                onRenamePalette = {}
            )
        }

        composeTestRule
            .onNodeWithTag("color_item_${palette.colorList[0]}")
            .performClick()

        assertThat(isInvoked).isTrue()
    }

    @Test
    fun checkIfonAddColorInvoked() {
        var isInvoked = false
        val viewState = PaletteDetailState.CurrentPalette(palette = palette)
        composeTestRule.setContent {
            PaletteDetailsContent(
                viewState = viewState,
                onDeletePalette = {},
                onSelectedColorIndex = {},
                onAddColor = {
                    isInvoked = true
                },
                onDeleteColor = {},
                onRenamePalette = {}
            )
        }

        composeTestRule
            .onNodeWithTag("add_color_tag")
            .performClick()

        assertThat(isInvoked).isTrue()
    }

    @Test
    fun checkIfonRenamePaletteInvoked() {
        var isInvoked = false
        val viewState = PaletteDetailState.CurrentPalette(palette = palette)
        composeTestRule.setContent {
            PaletteDetailsContent(
                viewState = viewState,
                onDeletePalette = {},
                onSelectedColorIndex = {},
                onAddColor = {},
                onDeleteColor = {},
                onRenamePalette = {
                    isInvoked = true
                }
            )
        }

        composeTestRule
            .onNodeWithTag("rename_palette_tag")
            .performClick()

        assertThat(isInvoked).isTrue()
    }

    @Test
    fun checkIfonDeleteColorPaletteInvoked() {
        var isInvoked = false
        val viewState = PaletteDetailState.CurrentPalette(palette = palette)
        composeTestRule.setContent {
            PaletteDetailsContent(
                viewState = viewState,
                onDeletePalette = {},
                onSelectedColorIndex = {},
                onAddColor = {},
                onDeleteColor = {
                    isInvoked = true
                },
                onRenamePalette = {}
            )
        }

        composeTestRule
            .onNodeWithTag("delete_color_tag")
            .performClick()

        assertThat(isInvoked).isTrue()
    }
}
