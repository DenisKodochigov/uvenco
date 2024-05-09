package com.example.uvenco.ui.screens.config

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.uvenco.data.DataRepository
import com.example.uvenco.entity.Coffee
import com.example.uvenco.entity.ErrorApp
import com.example.uvenco.ui.lg
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ConfigViewModel @Inject constructor(
    private val errorApp: ErrorApp,
    private val dataRepository: DataRepository
): ViewModel() {
    private val _configState = MutableStateFlow(
        ConfigState(
            onSave = { coffee -> onSave(coffee)},
            typesCoffee = emptyList(),))
    val configState: StateFlow<ConfigState> = _configState.asStateFlow()
    init {
        viewModelScope.launch(Dispatchers.IO) {
            dataRepository.getFlowList().collect{
                _configState.update {
                    currentState -> currentState.copy( typesCoffee = it.list ) }
            }
        }
    }

    private fun onSave(coffee: Coffee){
        lg("ConfigViewModel id: ${coffee.id}")
        viewModelScope.launch(Dispatchers.IO) {
            kotlin.runCatching { dataRepository.updateItem( coffee) }.fold(
                onSuccess = { },
                onFailure = { errorApp.errorApi(it.message!!) }
            )
        }
    }
    private fun templateMy( funDataRepository:() -> List<Coffee> ){
        viewModelScope.launch(Dispatchers.IO) {
            kotlin.runCatching { funDataRepository() }.fold(
                onSuccess = {  _configState.update {
                        currentState -> currentState.copy( typesCoffee = it ) } },
                onFailure = { errorApp.errorApi(it.message!!) }
            )
        }
    }
}