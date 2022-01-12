package com.akashk.palette.palettelist

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import com.akashk.palette.destinations.CreateNewPaletteScreenDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@ExperimentalMaterial3Api
@Composable
@Destination(start = true)
fun PaletteListScreen(
    navigator: DestinationsNavigator,
    viewModel: PaletteListViewModel = hiltViewModel(),
) {
    val viewState = viewModel.viewState.collectAsState()
    DisposableEffect(key1 = viewState.value) {
        if (viewState.value is PaletteListViewState.AddNewPalette) {
            // Navigate to CreateNewPaletteScreen.
            navigator.navigate(CreateNewPaletteScreenDestination)
        }
        onDispose { }
    }
    PaletteScreenContent(
        viewState = viewState.value,
        onAddClick = {
            viewModel.addNewPalette()
        },
    )
}
