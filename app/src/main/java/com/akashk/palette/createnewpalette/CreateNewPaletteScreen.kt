package com.akashk.palette.createnewpalette

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.akashk.palette.core.ui.components.PaletteTextField
import com.akashk.palette.core.ui.getString
import com.akashk.palette.destinations.ColorPickerScreenDestination
import com.akashk.palette.domain.data.Palette
import com.akashk.palette.ui.theme.PaletteTheme
import com.google.accompanist.insets.navigationBarsWithImePadding
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import java.util.UUID

@Destination
@Composable
fun CreateNewPaletteScreen(
    navigator: DestinationsNavigator,
    viewModel: CreateNewPaletteViewModel = hiltViewModel()
) {
    val viesState = viewModel.viewState.collectAsState()
    val palette = Palette(
        id = UUID.randomUUID().toString(),
        name = "",
        colorList = mutableListOf(),
        modifiedAt = System.currentTimeMillis()
    )
    CreateNewPaletteContent(
        viewState = viesState.value,
        onAddClick = {
            viewModel.onContinue(viesState.value.paletteName) {
                navigator.navigate(
                    ColorPickerScreenDestination(
                        palette = palette.copy(name = viesState.value.paletteName)
                    )
                ) {
                    navigator.popBackStack()
                }
            }
        },
        onTextChanged = { text ->
            viewModel.enterPaletteName(text)
        }
    )
}

@Composable
fun CreateNewPaletteContent(
    viewState: NewPaletteState,
    onTextChanged: (String) -> Unit,
    onAddClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(all = 16.dp)
            .navigationBarsWithImePadding(),
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val haptic = LocalHapticFeedback.current
        PaletteTextField(
            text = viewState.paletteName,
            labelText = "Enter Palette Name",
            modifier = Modifier
                .fillMaxWidth()
                .testTag("palette_name_text_field"),
            onTextChange = onTextChanged,
            errorMessage = viewState.paletteNameError?.getString()
        )
        Spacer(modifier = Modifier.height(60.dp))
        Button(
            onClick = {
                haptic.performHapticFeedback(HapticFeedbackType.LongPress)
                onAddClick.invoke()
            },
            modifier = Modifier.fillMaxWidth(0.9f)
        ) {
            Text(
                text = "Continue",
                color = MaterialTheme.colorScheme.onPrimary,
                style = TextStyle(
                    fontWeight = FontWeight.Bold
                )
            )
        }
    }
}

@Preview(
    name = "Night Mode - Palette Screen",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
)
@Preview(
    name = "Day Mode - Palette Screen",
    uiMode = Configuration.UI_MODE_NIGHT_NO,
)
@Composable
fun CreateNewPalettePreview() {
    PaletteTheme {
        val viewState = NewPaletteState(
            paletteName = "New Palette",
            paletteNameError = null
        )
        CreateNewPaletteContent(
            viewState = viewState,
            onTextChanged = {},
            onAddClick = {}
        )
    }
}
