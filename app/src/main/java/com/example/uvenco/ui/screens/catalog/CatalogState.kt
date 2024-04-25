package com.example.uvenco.ui.screens.catalog

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.Stable
import androidx.compose.runtime.mutableStateOf
import com.example.uvenco.entity.Coffee
import com.example.uvenco.entity.TickTime
import javax.inject.Singleton

@Singleton
data class CatalogState(
    @Stable var typesCoffee: List<Coffee> = emptyList(),
    @Stable var onClickItem: (Int) -> Unit = {},
)