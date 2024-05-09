package com.example.uvenco.ui.screens.config

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.Stable
import androidx.compose.runtime.mutableStateOf
import com.example.uvenco.entity.Coffee
import javax.inject.Singleton

@Singleton
data class ConfigState(
    val coffeeTemp: MutableState<Coffee?> = mutableStateOf(null),
    val enablesButton: MutableState<Boolean>  = mutableStateOf(false),
    @Stable var typesCoffee: List<Coffee> = emptyList(),
    @Stable var onSave: (Coffee) -> Unit = {},
)