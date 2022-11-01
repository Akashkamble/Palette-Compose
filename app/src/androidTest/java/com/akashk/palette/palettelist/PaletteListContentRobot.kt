package com.akashk.palette.palettelist

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.ComposeTestRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.akashk.palette.domain.data.Palette

class PaletteListContentRobot(
    val composeTestRule: ComposeTestRule
) {
    private val fab = composeTestRule
        .onNodeWithContentDescription("Add new palette")
    private val circularIndicator = composeTestRule
        .onNodeWithTag("loading palette list")
    private val emptyListImage = composeTestRule
        .onNodeWithContentDescription("Empty List")
    private val emptyListText = composeTestRule
        .onNodeWithText("Palette is not added yet.")
    private val emptyListSubText = composeTestRule
        .onNodeWithText("Click on the + to add new Palette.")

    fun fabVisible() {
        fab.assertIsDisplayed()
    }

    fun circularIndicatorVisible() {
        circularIndicator.assertIsDisplayed()
    }

    fun listWithPaletteIdsVisible(paletteList: List<Palette>) {
        paletteList.forEach {
            composeTestRule
                .onNodeWithTag("Palette_${it.id}")
                .assertIsDisplayed()
        }
    }

    fun clickFab() {
        fab.performClick()
    }

    fun clickPaletteWithId(palette: Palette) {
        composeTestRule
            .onNodeWithTag("Palette_${palette.id}")
            .performClick()
    }

    fun emptyListIconVisible() {
        emptyListImage.assertIsDisplayed()
    }

    fun emptyListTextVisible() {
        emptyListText.assertIsDisplayed()
    }

    fun emptyListSubTextVisible() {
        emptyListSubText.assertIsDisplayed()
    }
}

fun paletteListRobot(composeTestRule: ComposeTestRule, block: PaletteListContentRobot.() -> Unit) {
    PaletteListContentRobot(composeTestRule).apply(block)
}
