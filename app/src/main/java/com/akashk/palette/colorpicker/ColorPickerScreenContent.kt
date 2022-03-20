package com.akashk.palette.colorpicker

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.akashk.palette.R
import com.akashk.palette.core.ui.components.PaletteColorItem

@Composable
fun ColorPickerScreenContent(
    viewState: ColorPickerState,
    pickColor: (color: String) -> Unit,
    modifier: Modifier = Modifier
) {
    val haptic = LocalHapticFeedback.current
    Surface(modifier = modifier.fillMaxSize()) {
        Box(
            modifier = modifier,
            contentAlignment = Alignment.Center
        ) {
            var currentColor by remember { mutableStateOf(0XFFFFFF) }
            CameraXComposable(
                analyzer = PixelColorAnalyzer {
                    currentColor = it
                },
                modifier = modifier.testTag("camerax_tag")
            )
            Box(
                modifier = modifier
                    .size(20.dp)
                    .clip(CircleShape)
                    .background(Color(currentColor))
                    .border(
                        width = 4.dp,
                        color = Color(0xFF464141),
                        shape = CircleShape
                    )
                    .testTag("current_color_tag")
            )
            Column(
                modifier = modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                AddedColorList(colorList = (viewState as ColorPickerState.CurrentPalette).list)
                Button(
                    onClick = {
                        haptic.performHapticFeedback(HapticFeedbackType.LongPress)
                        pickColor.invoke(String.format("#%06X", 0xFFFFFF and currentColor))
                    },
                    shape = CircleShape,
                    modifier = modifier.testTag("pick_color_tag")
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_add_24),
                        contentDescription = "Add new palette",
                        tint = MaterialTheme.colorScheme.onPrimary
                    )
                }
                Spacer(modifier = Modifier.height(50.dp))
            }
        }
    }
}

@Composable
fun AddedColorList(
    colorList: List<String>,
    modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = Arrangement.Center
    ) {
        LazyRow(
            contentPadding = PaddingValues(8.dp),
            horizontalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.list_padding)),
            modifier = modifier,
        ) {
            items(colorList) { color ->
                PaletteColorItem(
                    color = color,
                    radius = 20.dp,
                    borderStrokeWidth = 2.dp,
                    modifier = modifier.testTag("color_tag_$color")
                )
            }
        }
    }
}
