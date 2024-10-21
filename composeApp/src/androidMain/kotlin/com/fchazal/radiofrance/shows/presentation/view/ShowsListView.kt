package com.fchazal.radiofrance.shows.presentation.view

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.fchazal.radiofrance.shows.presentation.model.ShowUI

@Composable

fun ShowsListView(
    shows: List<ShowUI>,
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        itemsIndexed(shows) { index, shows ->
            ShowListItemView(shows, (index % 2 == 0))
        }
    }
}