package com.akashk.palette.core.ui.components

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
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
    borderStrokeWidth: Dp = 4.dp,
    modifier: Modifier = Modifier
) {
    val shape = CircleShape
    Box(
        modifier = modifier
            .size(radius)
            .clip(shape)
            .background(color = color.toComposeColor)
            .border(
                width = borderStrokeWidth,
                color = Color(0xFF464141),
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