package com.akashk.palette.palettedetail.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import com.akashk.palette.utils.toComposeColor

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ColorGrid(
    colorList: List<String>,
    selectedIndex: Int,
    modifier: Modifier,
    onSelectedColorIndex: (index: Int) -> Unit
) {
    val haptic = LocalHapticFeedback.current
    val interactionSource = remember {
        MutableInteractionSource()
    }
    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 60.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        modifier = modifier.fillMaxHeight(0.7f)
    ) {

        itemsIndexed(colorList) { index, color ->
            Box(
                modifier = modifier
                    .size(50.dp)
                    .border(
                        width = 3.dp,
                        color = if (selectedIndex == index)
                            MaterialTheme.colorScheme.primary
                        else
                            Color(0x33E0D9D9),
                        shape = RoundedCornerShape(8.dp)
                    )
                    .clip(RoundedCornerShape(8.dp))
                    .background(color = color.toComposeColor)
                    .clickable(
                        indication = null,
                        interactionSource = interactionSource,
                        onClick = {
                            haptic.performHapticFeedback(HapticFeedbackType.LongPress)
                            onSelectedColorIndex.invoke(index)
                        }
                    )
                    .testTag("color_item_$color")
            ) {}
        }
    }
}
