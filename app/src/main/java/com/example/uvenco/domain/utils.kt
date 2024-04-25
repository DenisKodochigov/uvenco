package com.example.uvenco.domain

fun Int.pad(): String = this.toString().padStart(2, '0')
fun String.toDoubleMy(): Double = if (this.isNotEmpty()) this.toDouble() else 0.0