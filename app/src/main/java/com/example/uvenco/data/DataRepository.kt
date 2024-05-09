package com.example.uvenco.data

import androidx.datastore.core.DataStore
import com.example.uvenco.entity.Coffee
import com.example.uvenco.entity.ListCoffee
import kotlinx.collections.immutable.mutate
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DataRepository @Inject constructor(private val dataStore: DataStore<ListCoffee>){
   suspend fun updateItem(coffee: Coffee) {
        dataStore.updateData {
            it.copy(
                list = it.list.mutate { mutableListCoffee->
                    mutableListCoffee[coffee.id] = coffee.copy()
                }
            )
        }
    }
    fun getFlowList() = dataStore.data
}