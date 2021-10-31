package com.akashk.palette.palettelist

import android.content.res.Configuration
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.akashk.palette.R
import com.akashk.palette.ui.theme.PaletteTheme

@ExperimentalMaterial3Api
@Composable
fun PaletteListScreen(modifier: Modifier = Modifier) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
        floatingActionButton = {
            FloatingActionButton(
                onClick = { },
                containerColor = MaterialTheme.colorScheme.primary
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_add_24),
                    contentDescription = "Add new palette",
                    tint = MaterialTheme.colorScheme.onPrimary
                )
            }
        }
    ) {

    }
}


@ExperimentalMaterial3Api
@Preview(
    name = "Night Mode - Palette Scree",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
)
@Preview(
    name = "Day Mode - Palette Scree",
    uiMode = Configuration.UI_MODE_NIGHT_NO,
)
@Composable
fun PaletteScreenPreview() {
    PaletteTheme {
        PaletteListScreen()
    }
}