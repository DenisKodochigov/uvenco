package com.example.uvenco.entity

data class Coffee(
    var name: String = "",
    val size: Double = 0.0,
    var price: Double = 0.0,
    var free: Boolean = false,
    var typeCoffee: TypeCoffee = TypeCoffee.Black
)


