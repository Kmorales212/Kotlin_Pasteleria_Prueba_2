package com.example.pasteleriakotlin.ui.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pasteleriakotlin.datos.Usuario
import com.example.pasteleriakotlin.network.RetrofitClient
import kotlinx.coroutines.launch

class AuthViewModel : ViewModel() {

    var emailUsuarioLogueado: String? = null
        private set

    // Función para LOGIN (Ahora es asíncrona, no retorna Boolean directo)
    // Usamos un "callback" (onResult) para avisar a la pantalla si funcionó o no
    fun login(email: String, contrasena: String, onResult: (Boolean) -> Unit) {
        viewModelScope.launch {
            try {
                val usuarioLogin = Usuario(email = email, contrasena = contrasena)
                // Llamada al Microservicio
                val respuesta = RetrofitClient.apiService.login(usuarioLogin)

                // Si llegamos aquí sin error, el login fue exitoso
                emailUsuarioLogueado = respuesta.email
                onResult(true)
            } catch (e: Exception) {
                // Si falla (401 Unauthorized o error de red)
                Log.e("API_LOGIN", "Error: ${e.message}")
                onResult(false)
            }
        }
    }

    // Función para REGISTRO
    fun registrar(nombre: String, email: String, contrasena: String, onResult: (Boolean) -> Unit) {
        viewModelScope.launch {
            try {
                val nuevoUsuario = Usuario(nombre = nombre, email = email, contrasena = contrasena)
                // Llamada al Microservicio
                RetrofitClient.apiService.registrarUsuario(nuevoUsuario)

                // Si no hay error, registro exitoso
                onResult(true)
            } catch (e: Exception) {
                Log.e("API_REGISTRO", "Error: ${e.message}")
                onResult(false)
            }
        }
    }
}