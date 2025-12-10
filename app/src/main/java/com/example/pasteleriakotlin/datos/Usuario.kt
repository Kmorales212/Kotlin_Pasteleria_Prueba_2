package com.example.pasteleriakotlin.datos


import com.google.gson.annotations.SerializedName
data class Usuario(
    val id: Long? = null,
    val nombre: String? = null,
    val email: String,
    @SerializedName("password")
    val contrasena: String
)