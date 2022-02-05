package com.akashk.palette.colorpicker

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramcosta.composedestinations.annotation.Destination

@Destination
@Composable
fun ColorPickerScreen(
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
