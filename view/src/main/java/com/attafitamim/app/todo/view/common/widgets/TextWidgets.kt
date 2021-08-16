package com.attafitamim.app.todo.view.common.widgets

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun ErrorLabel(text: String, modifier: Modifier) {
    Text(
        text = text,
        color = Color.Red,
        modifier = modifier,
    )
}