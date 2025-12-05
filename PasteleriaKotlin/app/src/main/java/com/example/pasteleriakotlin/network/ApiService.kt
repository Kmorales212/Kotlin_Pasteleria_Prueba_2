package com.example.pasteleriakotlin.network

import com.example.pasteleriakotlin.datos.Producto
import com.example.pasteleriakotlin.datos.Usuario
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {

    @GET("usuarios")
    suspend fun listarUsuarios(): List<Usuario>

    @POST("usuarios")
    suspend fun registrarUsuario(@Body usuario: Usuario): Usuario

    @POST("usuarios/login")
    suspend fun login(@Body usuario: Usuario): Usuario
    @GET("productos")
    suspend fun obtenerProductos(): List<Producto>

    @POST("productos")
    suspend fun crearProducto(@Body producto: Producto): Producto
}