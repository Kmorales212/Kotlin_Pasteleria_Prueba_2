package com.example.pasteleriakotlin.ui.screens


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.pasteleriakotlin.navegacion.RUTA_LOGIN
import com.example.pasteleriakotlin.ui.viewModel.AuthViewModel
import com.example.pasteleriakotlin.navegacion.RUTA_REGISTRO


@Composable
fun RegistroScreen(navController: NavController, authViewModel: AuthViewModel = viewModel()) {

    var email by remember { mutableStateOf("") }
    var contrasena by remember { mutableStateOf("") }
    var errorRegistro by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Crear Cuenta", style = androidx.compose.material3.MaterialTheme.typography.headlineMedium)

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            singleLine = true
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = contrasena,
            onValueChange = { contrasena = it },
            label = { Text("Contraseña") },
            singleLine = true,
            visualTransformation = PasswordVisualTransformation()
        )

        Spacer(modifier = Modifier.height(16.dp))

        if (errorRegistro) {
            Text(
                "El email ya está en uso",
                color = androidx.compose.material3.MaterialTheme.colorScheme.error
            )
            Spacer(modifier = Modifier.height(8.dp))
        }

        Button(onClick = {

            if (authViewModel.registrar(email, contrasena)) {

                errorRegistro = false
                navController.navigate(RUTA_LOGIN) {
                    popUpTo(RUTA_REGISTRO) { inclusive = true }
                }
            } else {

                errorRegistro = true
            }
        }) {
            Text("Registrarse")
        }
    }
}