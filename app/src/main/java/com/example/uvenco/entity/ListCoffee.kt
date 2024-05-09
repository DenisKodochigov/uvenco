package com.example.uvenco.entity

import com.example.uvenco.data.datastore.MyPersistentListSerializer
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.persistentListOf
import kotlinx.serialization.Serializable

@Serializable
data class ListCoffee(
    @Serializable(with = MyPersistentListSerializer::class)
    val list: PersistentList<Coffee> = persistentListOf()
)
