package com.akashk.palette.palettelist

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.akashk.palette.R
import com.akashk.palette.core.ui.components.PaletteCircularProgressIndicator
import com.akashk.palette.domain.data.Palette
import com.akashk.palette.ui.theme.PaletteTheme
import com.google.accompanist.insets.navigationBarsPadding
import com.google.accompanist.insets.statusBarsPadding

@ExperimentalMaterial3Api
@Composable
fun PaletteScreenContent(
    viewState: PaletteListViewState,
    onAddClick: () -> Unit,
    onPaletteClick: (palette: Palette) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .navigationBarsPadding()
    ) {
        when {
            viewState.isLoading -> {
                PaletteCircularProgressIndicator(
                    modifier = Modifier
                        .wrapContentSize()
                        .align(Alignment.Center)
                        .testTag("loading palette list"),

                    )
            }
            viewState.palettes.isNotEmpty() -> {
                LoadedPaletteContent(
                    viewState = viewState,
                    onAddClick = onAddClick,
                    onPaletteClick = onPaletteClick,
                    modifier = modifier
                )
            }
            viewState.palettes.isEmpty() -> {
                EmptyPaletteList(onAddClick = onAddClick, modifier = modifier)
            }
        }
    }
}

@ExperimentalMaterial3Api
@Composable
private fun EmptyPaletteList(
    onAddClick: () -> Unit,
    modifier: Modifier
) {
    val haptic = LocalHapticFeedback.current
    Scaffold(
        modifier = modifier.fillMaxSize(),
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    haptic.performHapticFeedback(HapticFeedbackType.LongPress)
                    onAddClick.invoke()
                },
                containerColor = MaterialTheme.colorScheme.primary
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_add_24),
                    contentDescription = "Add new palette",
                    tint = MaterialTheme.colorScheme.onPrimary
                )
            }
        }
    ) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .statusBarsPadding(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_color_palette),
                contentDescription = "Empty List",
                modifier = modifier.requiredSize(200.dp)
            )
            Text(
                text = "Palette is not added yet.",
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal,
                color = MaterialTheme.colorScheme.onSurface,
            )
            Text(
                text = "Click on the + to add new Palette.",
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal,
                color = MaterialTheme.colorScheme.onSurface,
            )
        }
    }
}

@ExperimentalMaterial3Api
@Composable
private fun LoadedPaletteContent(
    viewState: PaletteListViewState,
    onAddClick: () -> Unit,
    onPaletteClick: (palette: Palette) -> Unit,
    modifier: Modifier
) {
    val haptic = LocalHapticFeedback.current
    Scaffold(
        modifier = modifier.fillMaxSize(),
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    haptic.performHapticFeedback(HapticFeedbackType.LongPress)
                    onAddClick.invoke()
                },
                containerColor = MaterialTheme.colorScheme.primary
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_add_24),
                    contentDescription = "Add new palette",
                    tint = MaterialTheme.colorScheme.onPrimary
                )
            }
        }
    ) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .statusBarsPadding()
        ) {
            PaletteList(
                palettes = viewState.palettes,
                onPaletteClick = onPaletteClick
            )
        }
    }
}

@ExperimentalMaterial3Api
@Preview(
    name = "Night Mode - Palette Screen",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
)
@Preview(
    name = "Day Mode - Palette Screen",
    uiMode = Configuration.UI_MODE_NIGHT_NO,
)
@Composable
fun PaletteScreenContentPreview() {
    val palettes = (1..5).map { index ->
        Palette(
            id = index.toString(),
            name = "Palette $index",
            colorList = mutableListOf("#6750a4", "#4534ff", "#0004fc", "#6750d8"),
            modifiedAt = System.currentTimeMillis()
        )
    }
    val viewState =
        PaletteListViewState(isLoading = false, palettes = palettes, errorMessage = null)
    PaletteTheme {
        PaletteScreenContent(
            viewState = viewState,
            onAddClick = {},
            onPaletteClick = {}
        )
    }
}
