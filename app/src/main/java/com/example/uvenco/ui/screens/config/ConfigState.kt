package com.example.uvenco.ui.screens.config

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.Stable
import androidx.compose.runtime.mutableStateOf
import com.example.uvenco.entity.Coffee
import com.example.uvenco.entity.TickTime
import com.example.uvenco.entity.TypeCoffee
import javax.inject.Singleton

@Singleton
data class ConfigState(
    @Stable var id: Int = 0,
    val coffeeTemp: MutableState<Coffee?> = mutableStateOf(null),
    val enablesButton: MutableState<Boolean>  = mutableStateOf(false),
    @Stable var typesCoffee: List<Coffee> = emptyList(),
    @Stable var onSave: (Int, Coffee) -> Unit = {_,_->},
)