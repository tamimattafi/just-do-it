package com.attafitamim.app.todo.view.common.widgets

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.attafitamim.app.todo.view.common.styles.DefaultPadding

internal fun LazyListScope.LoadingPage() {
    item {
        val matchParent = DefaultPadding().fillParentMaxSize()
        CircularProgressIndicator(modifier = matchParent)
    }
}

internal fun LazyListScope.LoadingItem() {
    item {
        val padding = DefaultPadding()
        CircularProgressIndicator(modifier = padding)
    }
}

internal fun LazyListScope.ErrorItem(error: LoadState.Error) {
    item {
        val errorLabel = error.error.localizedMessage ?: error.toString()
        val padding = DefaultPadding()
        ErrorLabel(text = errorLabel, modifier = padding)
    }
}

internal fun LazyListScope.ErrorPage(error: LoadState.Error) {
    item {
        val errorLabel = error.error.localizedMessage ?: error.toString()
        val matchParent = Modifier.fillParentMaxSize()
        ErrorLabel(text = errorLabel, modifier = matchParent)
    }
}

internal fun LazyListScope.StateItem(lazyItems: LazyPagingItems<*>?) {
    if (lazyItems == null) {
        LoadingPage()
        return
    }

    val loadState = lazyItems.loadState
    when {
        loadState.refresh is LoadState.Loading -> LoadingPage()
        loadState.append is LoadState.Loading -> LoadingItem()

        loadState.refresh is LoadState.Error -> {
            val error = lazyItems.loadState.refresh as LoadState.Error
            ErrorPage(error = error)
        }

        loadState.append is LoadState.Error -> {
            val error = lazyItems.loadState.refresh as LoadState.Error
            ErrorItem(error = error)
        }
    }
}