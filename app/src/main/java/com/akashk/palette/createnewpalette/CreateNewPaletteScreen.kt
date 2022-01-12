package com.akashk.palette.createnewpalette

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.akashk.palette.core.ui.components.PaletteTextField
import com.akashk.palette.core.ui.getString
import com.akashk.palette.ui.theme.PaletteTheme

@Composable
fun CreateNewPaletteScreen(
    viewModel: CreateNewPaletteViewModel = hiltViewModel()
) {
    val viesState = viewModel.viewState.collectAsState()
    CreateNewPaletteContent(
        viewState = viesState.value,
        onAddClick = {
            viewModel.onContinue(viesState.value.paletteName)
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
    modifier: Modifier = Modifier
        .fillMaxSize()
        .padding(all = 16.dp),
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        PaletteTextField(
            text = viewState.paletteName,
            labelText = "Enter Palette Name",
            modifier = Modifier.fillMaxWidth(),
            onTextChange = onTextChanged,
            errorMessage = if(viewState is NewPaletteState.Active) viewState.paletteNameError?.getString() else ""
        )
        Spacer(modifier = Modifier.height(60.dp))
        Button(
            onClick = onAddClick,
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
        val viewState = NewPaletteState.Active(
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
