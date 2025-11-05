package com.example.pasteleriakotlin.datos

import androidx.annotation.DrawableRes
data class Producto(
    val id: Int,
    val nombre: String,
    val precio: Double,
    @DrawableRes val imagen: Int
)