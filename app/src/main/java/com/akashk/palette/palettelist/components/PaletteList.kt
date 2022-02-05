package com.akashk.palette.palettelist

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import com.akashk.palette.R
import com.akashk.palette.domain.data.Palette
import com.akashk.palette.ui.theme.PaletteTheme

@Composable
fun PaletteList(
    palettes: List<Palette>,
    onPaletteClick: () -> Unit,
    modifier: Modifier = Modifier
) {

    LazyColumn(
        contentPadding = PaddingValues(dimensionResource(id = R.dimen.list_padding)),
        verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.list_padding)),
        modifier = modifier,
    ) {
        items(palettes) { palette ->
            PaletteListItem(
                palette,
                onPaletteClick = onPaletteClick
            )
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
private fun PaletteListPreview() {
    val palettes = (1..5).map { index ->
        Palette(
            id = index,
            name = "Palette $index",
            colorList = listOf("#6750a4", "#4534ff", "#0004fc", "#6750d8"),
            modifiedAt = System.currentTimeMillis()
        )
    }

    PaletteTheme {
        PaletteList(
            palettes = palettes,
            onPaletteClick = {}
        )
    }
}
