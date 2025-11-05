package com.example.pasteleriakotlin.ui.viewModel

import androidx.lifecycle.ViewModel
import com.example.pasteleria.data.DatosUsuarios
import com.example.pasteleria.data.Usuario
import kotlin.collections.any
import kotlin.collections.find



class AuthViewModel : ViewModel() {


    var emailUsuarioLogueado: String? = null
        private set


    fun login(email: String, contrasena: String): Boolean {

        val usuarioEncontrado = DatosUsuarios.listaUsuarios.find {
            it.email == email && it.contrasena == contrasena
        }

        if (usuarioEncontrado != null) {
            emailUsuarioLogueado = email
            return true
        }

        return false
    }


    fun registrar(email: String, contrasena: String): Boolean {

        val emailYaExiste = DatosUsuarios.listaUsuarios.any { it.email == email }

        if (emailYaExiste) {
            return false
        }


        val nuevoUsuario = Usuario(email = email, contrasena = contrasena)


        DatosUsuarios.listaUsuarios.add(nuevoUsuario)

        return true
    }
}