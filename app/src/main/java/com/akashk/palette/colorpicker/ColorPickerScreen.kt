package com.akashk.palette.colorpicker

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.hilt.navigation.compose.hiltViewModel
import com.akashk.palette.domain.data.Palette
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionStatus
import com.google.accompanist.permissions.rememberPermissionState
import com.ramcosta.composedestinations.annotation.Destination

@OptIn(ExperimentalPermissionsApi::class)
@Destination
@Composable
fun ColorPickerScreen(
    palette: Palette,
    viewModel: ColorPickerViewModel = hiltViewModel()
) {
    val viewState = viewModel.viewState.collectAsState()
    val cameraPermissionState = rememberPermissionState(
        android.Manifest.permission.CAMERA
    )
    LaunchedEffect(key1 = Unit) {
        cameraPermissionState.launchPermissionRequest()
    }
    when (cameraPermissionState.status) {
        PermissionStatus.Granted -> {
            ColorPickerScreenContent(
                viewState = viewState.value,
                pickColor = { color ->
                    viewModel.pickColor(color = color)
                }
            )
        }
        is PermissionStatus.Denied -> {
            Surface(modifier = Modifier.fillMaxSize()) {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxSize()
                ) {
                    Text(
                        text = "Require Camera Permission.\nPlease Grant permission to access Camera",
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}
