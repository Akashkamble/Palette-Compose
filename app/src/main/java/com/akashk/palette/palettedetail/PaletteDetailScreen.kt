package com.akashk.palette.palettedetail

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.akashk.palette.core.ui.components.PaletteCircularProgressIndicator
import com.akashk.palette.destinations.ColorPickerScreenDestination
import com.akashk.palette.domain.data.Palette
import com.akashk.palette.palettedetail.components.ColorBox
import com.akashk.palette.palettedetail.components.ColorGrid
import com.akashk.palette.palettedetail.components.PaletteDetailsBottomNavigationBar
import com.akashk.palette.palettedetail.components.PaletteDetailsToolBar
import com.google.accompanist.insets.navigationBarsPadding
import com.google.accompanist.insets.statusBarsPadding
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@Composable
fun PaletteDetailScreen(
    palette: Palette,
    navigator: DestinationsNavigator,
    viewModel: PaletteDetailsViewModel = hiltViewModel(),
) {
    val viewState = viewModel.viewState.collectAsState()
    val context = LocalContext.current
    DisposableEffect(key1 = viewState.value) {
        if (viewState.value is PaletteDetailState.CloseDetailsScreen) {
            navigator.popBackStack()
        }
        onDispose { }
    }

    LaunchedEffect(key1 = Unit) {
        viewModel.fetchPaletteById(palette.id)
    }
    PaletteDetailsContent(
        viewState = viewState.value,
        onDeletePalette = {
            viewModel.deletePalette()
        },
        onSelectedColorIndex = { selectedIndex ->
            viewModel.updateSelectedIndex(selectedIndex)
        },
        onAddColor = {
            navigator.navigate(ColorPickerScreenDestination(palette = it))
        },
        onDeleteColor = {
            viewModel.deleteSelectedColor()
        },
        onRenamePalette = {
            Toast.makeText(context, "Not Implemented Yet", Toast.LENGTH_LONG).show()
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PaletteDetailsContent(
    viewState: PaletteDetailState,
    modifier: Modifier = Modifier,
    onDeletePalette: () -> Unit,
    onSelectedColorIndex: (index: Int) -> Unit,
    onAddColor: (palette: Palette) -> Unit,
    onDeleteColor: () -> Unit,
    onRenamePalette: () -> Unit,
) {
    when (viewState) {
        is PaletteDetailState.CurrentPalette -> {
            Scaffold(
                modifier = modifier
                    .navigationBarsPadding()
                    .statusBarsPadding(),
                topBar = {
                    PaletteDetailsToolBar(
                        name = viewState.paletteName,
                        modifier = modifier,
                        onDeletePalette = onDeletePalette
                    )
                },
                bottomBar = {
                    PaletteDetailsBottomNavigationBar(
                        modifier = modifier,
                        onDeleteColor = onDeleteColor,
                        onAddColors = { onAddColor(viewState.palette) },
                        onRenamePalette = onRenamePalette
                    )
                },
            ) {
                val selectedColor = viewState.palette.colorList[viewState.selectedIndex]
                Column(
                    modifier = modifier
                        .fillMaxSize()
                        .padding(
                            start = 16.dp,
                            end = 16.dp,
                            top = it.calculateTopPadding()
                        )
                ) {
                    ColorBox(
                        modifier = modifier,
                        color = selectedColor
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    ColorGrid(
                        colorList = viewState.palette.colorList,
                        selectedIndex = viewState.selectedIndex,
                        modifier = modifier,
                    ) { selectedIndex ->
                        onSelectedColorIndex.invoke(selectedIndex)
                    }
                }
            }
        }
        is PaletteDetailState.ErrorState -> {
        }
        is PaletteDetailState.IsLoading -> {
            PaletteCircularProgressIndicator()
        }
        else -> {
            Text(text = "Something went wrong")
        }
    }
}
