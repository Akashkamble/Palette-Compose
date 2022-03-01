package com.akashk.palette.palettedetail.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun PaletteDetailsBottomNavigationBar(
    modifier: Modifier,
    onDeleteColor: () -> Unit,
    onAddColors: () -> Unit,
    onRenamePalette: () -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
    ) {
        BottomNavigation(
            backgroundColor = MaterialTheme.colorScheme.primary,
            elevation = 0.dp
        ) {
            BottomNavigationItem(
                onClick = onDeleteColor,
                icon = {
                    Icon(
                        imageVector = Icons.Outlined.Delete,
                        contentDescription = "Delete selected color",
                        tint = MaterialTheme.colorScheme.onPrimary
                    )
                },
                selected = true,
                label = {
                    Text(
                        text = "Delete Color",
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                }
            )
            BottomNavigationItem(
                onClick = onAddColors,
                icon = {
                    Icon(
                        imageVector = Icons.Outlined.Add,
                        contentDescription = "Delete selected color",
                        tint = MaterialTheme.colorScheme.onPrimary
                    )
                },
                selected = true,
                label = {
                    Text(
                        text = "Add Colors",
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                }
            )
            BottomNavigationItem(
                onClick = onRenamePalette,
                icon = {
                    Icon(
                        imageVector = Icons.Outlined.Edit,
                        contentDescription = "Rename Palette",
                        tint = MaterialTheme.colorScheme.onPrimary
                    )
                },
                selected = true,
                label = {
                    Text(
                        text = "Rename Palette",
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                }
            )
        }
    }
}
