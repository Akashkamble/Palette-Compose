package com.akashk.palette.palettelist

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel

@ExperimentalMaterial3Api
@Composable
fun PaletteListScreen(
    viewModel: PaletteListViewModel = hiltViewModel(),
) {
    val viewState = viewModel.viewState.collectAsState()
    PaletteScreenContent(
        viewState = viewState.value,
        onAddClick = {
            viewModel.addNewPalette()
        },
    )
}


