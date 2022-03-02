package com.akashk.palette.palettedetail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
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
    PaletteDetailsContent(
        viewState = viewState.value,
        onDeletePalette = {},
        onSelectedColorIndex = { selectedIndex ->
            viewModel.updateSelectedIndex(selectedIndex)
        },
        onAddColor = {},
        onDeleteColor = {
            viewModel.deleteSelectedColor()
        },
        onRenamePalette = {
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
    onAddColor: () -> Unit,
    onDeleteColor: () -> Unit,
    onRenamePalette: () -> Unit
) {
    /*LaunchedEffect(key1 = viewState){
        Log.d("Test", viewState.toString())
    }*/
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
                onAddColors = onAddColor,
                onRenamePalette = onRenamePalette
            )
        },
    ) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            ColorBox(
                modifier = modifier,
                color = viewState.paletteColorList[viewState.selectedIndex]
            )
            Spacer(modifier = Modifier.height(20.dp))
            ColorGrid(
                colorList = viewState.paletteColorList,
                selectedIndex = viewState.selectedIndex,
                modifier = modifier,
            ) { selectedIndex ->
                onSelectedColorIndex.invoke(selectedIndex)
            }
        }
    }
}
