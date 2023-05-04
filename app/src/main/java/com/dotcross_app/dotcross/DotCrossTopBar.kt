package com.dotcross_app.dotcross

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun DotCrossTopAppBar(
    text: String,
    backEnabled: Boolean,
    modifier: Modifier,
    navigateUp: () -> Unit = {}
) {
    if (backEnabled) {
        TopAppBar(
            title = { Text(text) },
            modifier = modifier,
            navigationIcon = {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = ""
                    )
                }
            }
        )
    } else {
        TopAppBar(title = { Text(text) }, modifier = modifier)
    }
}