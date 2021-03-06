package com.akashk.palette.palettelist

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.akashk.palette.core.ui.components.PaletteCard
import com.akashk.palette.core.ui.components.PaletteColorItem
import com.akashk.palette.domain.data.Palette
import com.akashk.palette.ui.theme.PaletteTheme

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun PaletteListItem(
    palette: Palette,
    onPaletteClick: (palette: Palette) -> Unit,
    modifier: Modifier = Modifier,
) {
    val haptic = LocalHapticFeedback.current
    PaletteCard(
        shape = RoundedCornerShape(size = 14.dp)
    ) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .clickable(
                    onClick = {
                        haptic.performHapticFeedback(HapticFeedbackType.LongPress)
                        onPaletteClick.invoke(palette)
                    }
                )
        ) {
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = palette.name,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
            )
            Spacer(modifier = Modifier.height(20.dp))
            ColorList(palette.colorList)
        }
    }
}

@Composable
fun ColorList(
    colorList: List<String>,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = if (!isSystemInDarkTheme()) Color(color = 0x48D3D3D3) else Color(color = 0x1FD3D3D3),
                shape = RoundedCornerShape(size = 14.dp)
            ),

    ) {
        LazyRow(
            contentPadding = PaddingValues(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = modifier,
        ) {
            items(colorList) { color ->
                PaletteColorItem(
                    color = color,
                    radius = 30.dp,
                    borderStrokeWidth = 2.dp
                )
            }
        }
    }
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
private fun PaletteListItemPreview() {
    val palette = Palette(
        id = "1",
        name = "Palette Name",
        colorList = mutableListOf("#6750a4", "#4534ff", "#0004fc", "#6750d8"),
        modifiedAt = System.currentTimeMillis()
    )

    PaletteTheme {
        PaletteListItem(
            palette = palette,
            onPaletteClick = {}
        )
    }
}
