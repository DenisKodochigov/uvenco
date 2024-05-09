package com.example.uvenco.entity

interface CoffeeInterface {
    var id: Int
    var name: String
    val size: Double
    var price: Int
    var free: Boolean
    var typeCoffee: TypeCoffee
}