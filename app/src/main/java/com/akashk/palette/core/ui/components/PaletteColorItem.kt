package com.akashk.palette.core.ui.components

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.akashk.palette.ui.theme.PaletteTheme
import com.akashk.palette.utils.toComposeColor

@Composable
fun PaletteColorItem(
    color: String,
    radius: Dp,
    modifier: Modifier = Modifier,
    borderStrokeWidth: Dp = 4.dp
) {
    val shape = CircleShape
    Box(
        modifier = modifier
            .size(radius)
            .clip(shape)
            .background(color = color.toComposeColor)
            .border(
                width = borderStrokeWidth,
                color = if (!isSystemInDarkTheme()) Color(color = 0xFF464141) else Color(color = 0xFF363434),
                shape = CircleShape
            )
    )
}

@Preview(
    name = "Night Mode",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
)
@Preview(
    name = "Day Mode",
    uiMode = Configuration.UI_MODE_NIGHT_NO,
)
@Composable
@Suppress("UnusedPrivateMember")
private fun PaletteColorItemPreview() {
    val color = "#6750a4"

    PaletteTheme {
        PaletteColorItem(color = color, radius = 100.dp)
    }
}
