package com.example.uvenco.ui.screens.catalog

import androidx.compose.runtime.Stable
import com.example.uvenco.entity.Coffee
import javax.inject.Singleton

@Singleton
data class CatalogState(
    @Stable var typesCoffee: List<Coffee> = emptyList(),
    @Stable var onClickItem: (Int) -> Unit = {},
)