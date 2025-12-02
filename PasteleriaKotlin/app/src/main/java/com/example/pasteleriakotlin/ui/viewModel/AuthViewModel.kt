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


    fun login(email: String, contrasena: String, onResult: (Boolean) -> Unit) {
        viewModelScope.launch {
            try {
                val usuarioLogin = Usuario(email = email, contrasena = contrasena)
                val respuesta = RetrofitClient.apiService.login(usuarioLogin)

                emailUsuarioLogueado = respuesta.email
                onResult(true)
            } catch (e: Exception) {
                Log.e("API_LOGIN", "Error: ${e.message}")
                onResult(false)
            }
        }
    }

    fun registrar(nombre: String, email: String, contrasena: String, onResult: (Boolean) -> Unit) {
        viewModelScope.launch {
            try {
                val nuevoUsuario = Usuario(nombre = nombre, email = email, contrasena = contrasena)
                RetrofitClient.apiService.registrarUsuario(nuevoUsuario)

                onResult(true)
            } catch (e: Exception) {
                Log.e("API_REGISTRO", "Error: ${e.message}")
                onResult(false)
            }
        }
    }
}