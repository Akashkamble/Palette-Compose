package com.akashk.palette.palettedetail.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.akashk.palette.utils.toComposeColor

@Composable
fun ColorBox(modifier: Modifier, color: String) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = Color(0x33E0D9D9)
            )
            .padding(start = 20.dp, end = 20.dp, top = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = modifier
                .fillMaxWidth()
                .fillMaxHeight(0.25f)
                .clip(RoundedCornerShape(14.dp))
                .background(color.toComposeColor),
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = color,
            color = MaterialTheme.colorScheme.onSurface,
            fontSize = 16.sp
        )
        Spacer(modifier = Modifier.height(10.dp))
    }
}
