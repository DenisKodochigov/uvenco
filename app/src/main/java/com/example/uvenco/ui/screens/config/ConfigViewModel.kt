package com.example.uvenco.ui.screens.config

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.uvenco.data.DataRepository
import com.example.uvenco.entity.Coffee
import com.example.uvenco.entity.ErrorApp
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
            onSave = { id, coffee -> onSave(id, coffee)},
            typesCoffee = emptyList(),))
    val configState: StateFlow<ConfigState> = _configState.asStateFlow()
    init { templateMy { dataRepository.getTypesCoffee() } }

    private fun onSave(id: Int, coffee: Coffee){
        templateMy { dataRepository.onSave(id, coffee) }
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