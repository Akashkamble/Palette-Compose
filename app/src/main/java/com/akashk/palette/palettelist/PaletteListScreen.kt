package com.akashk.palette.palettelist

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import com.akashk.palette.destinations.CreateNewPaletteScreenDestination
import com.akashk.palette.destinations.PaletteDetailScreenDestination
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

    PaletteScreenContent(
        viewState = viewState.value,
        onAddClick = {
            navigator.navigate(CreateNewPaletteScreenDestination)
        },
        onPaletteClick = { palette ->
            navigator.navigate(PaletteDetailScreenDestination(palette = palette))
        }
    )
}
