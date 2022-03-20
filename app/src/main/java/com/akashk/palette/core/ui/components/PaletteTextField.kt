package com.akashk.palette.core.ui.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ContentAlpha
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.TextFieldDefaults.outlinedTextFieldColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.akashk.palette.ui.theme.PaletteTheme

@Composable
fun PaletteTextField(
    text: String,
    onTextChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    labelText: String = "",
    maxLines: Int = 1,
    errorMessage: String? = null
) {

    val labelComposable: (@Composable () -> Unit)? = labelText.let {
        @Composable {
            Text(
                text = labelText,
            )
        }
    }

    Column {
        OutlinedTextField(
            label = labelComposable,
            value = text,
            onValueChange = onTextChange,
            modifier = modifier,
            colors = md3TextFieldColors(),
            textStyle = TextStyle(
                fontSize = 18.sp
            ),
            maxLines = maxLines,
            singleLine = true,
            isError = !errorMessage.isNullOrEmpty()
        )
        if (!errorMessage.isNullOrEmpty()) {
            Text(
                text = errorMessage,
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.padding(horizontal = 10.dp)
            )
        }
    }
}

@Composable
private fun md3TextFieldColors(
    textColor: Color = MaterialTheme.colorScheme.onBackground,
    disabledTextColor: Color = textColor.copy(ContentAlpha.disabled),
    backgroundColor: Color = Color.Transparent,
    cursorColor: Color = MaterialTheme.colorScheme.primary,
    errorCursorColor: Color = MaterialTheme.colorScheme.error,
    focusedBorderColor: Color =
        MaterialTheme.colorScheme.primary.copy(alpha = ContentAlpha.high),
    unfocusedBorderColor: Color =
        MaterialTheme.colorScheme.onSurface.copy(alpha = ContentAlpha.disabled),
    disabledBorderColor: Color = unfocusedBorderColor.copy(alpha = ContentAlpha.disabled),
    errorBorderColor: Color = MaterialTheme.colorScheme.error,
    leadingIconColor: Color =
        MaterialTheme.colorScheme.onSurface.copy(alpha = TextFieldDefaults.IconOpacity),
    disabledLeadingIconColor: Color = leadingIconColor.copy(alpha = ContentAlpha.disabled),
    errorLeadingIconColor: Color = leadingIconColor,
    trailingIconColor: Color =
        MaterialTheme.colorScheme.onSurface.copy(alpha = TextFieldDefaults.IconOpacity),
    disabledTrailingIconColor: Color = trailingIconColor.copy(alpha = ContentAlpha.disabled),
    errorTrailingIconColor: Color = MaterialTheme.colorScheme.error,
    focusedLabelColor: Color =
        MaterialTheme.colorScheme.primary.copy(alpha = ContentAlpha.high),
    unfocusedLabelColor: Color = MaterialTheme.colorScheme.onSurface.copy(
        ContentAlpha.medium
    ),
    disabledLabelColor: Color = unfocusedLabelColor.copy(ContentAlpha.disabled),
    errorLabelColor: Color = MaterialTheme.colorScheme.error,
    placeholderColor: Color = MaterialTheme.colorScheme.onSurface.copy(
        ContentAlpha.medium
    ),
    disabledPlaceholderColor: Color = placeholderColor.copy(ContentAlpha.disabled),
) = outlinedTextFieldColors(
    textColor,
    disabledTextColor,
    backgroundColor,
    cursorColor,
    errorCursorColor,
    focusedBorderColor,
    unfocusedBorderColor,
    disabledBorderColor,
    errorBorderColor,
    leadingIconColor,
    disabledLeadingIconColor,
    errorLeadingIconColor,
    trailingIconColor,
    disabledTrailingIconColor,
    errorTrailingIconColor,
    focusedLabelColor,
    unfocusedLabelColor,
    disabledLabelColor,
    errorLabelColor,
    placeholderColor,
    disabledPlaceholderColor,
)

@Preview(
    name = "Night Mode",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
)
@Preview(
    name = "Day Mode",
    uiMode = Configuration.UI_MODE_NIGHT_NO,
)
@Composable
fun PaletteTextFieldPreview() {
    PaletteTheme {
        PaletteTextField(text = "", onTextChange = {})
    }
}
