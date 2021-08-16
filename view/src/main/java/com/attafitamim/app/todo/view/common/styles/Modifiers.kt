package com.attafitamim.app.todo.view.common.styles

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import com.attafitamim.app.todo.view.R

@Composable
fun DefaultPadding(): Modifier {
    val padding = dimensionResource(R.dimen.default_padding)
    return Modifier.padding(all = padding)
}