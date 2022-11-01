package com.akashk.palette.palettedetails

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.junit4.createAndroidComposeRule
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

        paletteDetailsScreenRobot(composeTestRule) {
            /*----Palette ToolBar Nodes-----*/
            toolBarWithPaletteNameVisible(palette)
            deletePaletteIconVisible()

            /*----Palette BottomNav Nodes-----*/
            allNavItemsVisible()

            /*----Palette ColorBox Nodes-----*/
            colorBoxVisible()
            hexColorTextVisible(palette)

            /*----Palette ColorGrid Nodes-----*/
            colorGridItemsVisible(palette)
        }
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

        paletteDetailsScreenRobot(composeTestRule) {
            deletePaletteClicked()
        }

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

        paletteDetailsScreenRobot(composeTestRule) {
            colorItemAtIndexClicked(palette, 0)
        }
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

        paletteDetailsScreenRobot(composeTestRule) {
            addColorNavItemClicked()
        }

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

        paletteDetailsScreenRobot(composeTestRule) {
            renamePaletteNavItemClicked()
        }

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

        paletteDetailsScreenRobot(composeTestRule) {
            deleteColorNavItemClicked()
        }

        assertThat(isInvoked).isTrue()
    }
}
