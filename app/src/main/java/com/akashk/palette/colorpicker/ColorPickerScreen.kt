package com.akashk.palette.colorpicker

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import com.akashk.palette.domain.data.Palette
import com.ramcosta.composedestinations.annotation.Destination

@Destination
@Composable
fun ColorPickerScreen(
    palette: Palette,
    viewModel: ColorPickerViewModel = hiltViewModel()
) {
    val viewState = viewModel.viewState.collectAsState()
    ColorPickerScreenContent(
        viewState = viewState.value,
        pickColor = { color ->
            viewModel.pickColor(color = color)
        }
    )
}
