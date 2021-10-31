package com.akashk.palette.colorpicker

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp


@Composable
fun ColorPickerScreen(modifier: Modifier = Modifier) {
    Surface(modifier = modifier.fillMaxSize()) {
        Box(
            modifier = modifier,
            contentAlignment = Alignment.Center
        ) {
            var currentColor by remember { mutableStateOf(0XFFFFFF) }
            CameraXComposable(
                analyzer = PixelColorAnalyzer {
                    currentColor = it
                },
            )
            Box(
                modifier = modifier
                    .size(20.dp)
                    .clip(CircleShape)
                    .background(Color(currentColor))

            )
            Column(
                modifier = modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = String.format("#%06X", 0xFFFFFF and currentColor),
                    modifier = modifier.border(
                        width = 1.dp,
                        color = MaterialTheme.colorScheme.primary
                    ),
                )
                Spacer(modifier = modifier.height(100.dp))
            }

        }
    }
}