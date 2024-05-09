package com.example.uvenco.ui.screens.catalog

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.uvenco.data.DataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CatalogViewModel @Inject constructor(
    private val dataRepository: DataRepository
): ViewModel() {
    private val _catalogState = MutableStateFlow(CatalogState(typesCoffee = emptyList(),))
    val catalogState: StateFlow<CatalogState> = _catalogState.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            dataRepository.getFlowList().collect{
                _catalogState.update {
                    currentState -> currentState.copy( typesCoffee = it.list ) }
            }
        }
    }
}