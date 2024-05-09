package com.example.uvenco.entity

import kotlinx.serialization.Serializable

@Serializable
data class Coffee (
    override var id: Int = 0,
    override var name: String = "",
    override val size: Double = 0.0,
    override var price: Int = 0,
    override var free: Boolean = false,
    override var typeCoffee: TypeCoffee = TypeCoffee.Milk,
): CoffeeInterface


