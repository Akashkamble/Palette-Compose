package com.akashk.palette.palettedetails

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.ComposeTestRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.akashk.palette.domain.data.Palette

class PaletteDetailScreenRobot(
    private val composeTestRule: ComposeTestRule
) {
    private val deletePaletteIcon = composeTestRule
        .onNodeWithContentDescription("Delete whole palette")

    private val deleteColorNavItem = composeTestRule
        .onNodeWithTag("delete_color_tag")

    private val addColorNavItem = composeTestRule
        .onNodeWithTag("add_color_tag")

    private val renamePaletteNavItem = composeTestRule
        .onNodeWithTag("rename_palette_tag")

    private val colorBox = composeTestRule
        .onNodeWithTag("color_box_tag")

    fun colorBoxVisible() {
        colorBox.assertIsDisplayed()
    }

    fun hexColorTextVisible(palette: Palette) {
        composeTestRule
            .onNodeWithText(palette.colorList.get(0))
            .assertIsDisplayed()
    }

    fun allNavItemsVisible() {
        deleteColorNavItem.assertIsDisplayed()
        addColorNavItem.assertIsDisplayed()
        renamePaletteNavItem.assertIsDisplayed()
    }

    fun deletePaletteIconVisible() {
        deletePaletteIcon.assertIsDisplayed()
    }

    fun toolBarWithPaletteNameVisible(palette: Palette) {
        composeTestRule
            .onNodeWithText(palette.name)
            .assertIsDisplayed()
    }

    fun colorGridItemsVisible(palette: Palette) {
        palette.colorList.forEach {
            composeTestRule
                .onNodeWithTag("color_item_$it")
                .assertIsDisplayed()
        }
    }

    fun deletePaletteClicked() {
        deletePaletteIcon.performClick()
    }

    fun colorItemAtIndexClicked(palette: Palette, index: Int) {
        composeTestRule
            .onNodeWithTag("color_item_${palette.colorList[index]}")
            .performClick()
    }

    fun addColorNavItemClicked() {
        addColorNavItem.performClick()
    }

    fun renamePaletteNavItemClicked() {
        renamePaletteNavItem.performClick()
    }

    fun deleteColorNavItemClicked() {
        deleteColorNavItem.performClick()
    }
}

fun paletteDetailsScreenRobot(composeTestRule: ComposeTestRule, block: (PaletteDetailScreenRobot.() -> Unit)) {
    PaletteDetailScreenRobot(composeTestRule).apply(block)
}
