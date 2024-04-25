package com.example.uvenco.domain

fun String.toDoubleMy(): Double = if (this.isNotEmpty()) this.toDouble() else 0.0