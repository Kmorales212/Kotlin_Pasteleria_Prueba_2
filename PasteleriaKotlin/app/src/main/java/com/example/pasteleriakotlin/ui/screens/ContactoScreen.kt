package com.example.pasteleriakotlin.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.pasteleriakotlin.navegacion.RUTA_CONTACTO

@Composable
fun ContactoScreen(navController: NavController) {
    Scaffold(
        bottomBar = {

            BottomNavigationBar(navController = navController, currentRoute = RUTA_CONTACTO)
        }
    ) { innerPadding ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            contentAlignment = Alignment.TopCenter
        ) {
            Column(
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    "Nuestras Sucursales",
                    style = MaterialTheme.typography.headlineMedium
                )

                Spacer(modifier = Modifier.height(16.dp))


                Text(
                    "Sucursal Centro",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )
                Text("Dirección: Avenida Ficticia 123, Centro")
                Text("Teléfono: +56 2 2123 4567")

                Spacer(modifier = Modifier.height(16.dp))


                Text(
                    "Sucursal Oriente",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )
                Text("Dirección: Calle Inventada 456, Oriente")
                Text("Teléfono: +56 2 2987 6543")

                Spacer(modifier = Modifier.height(16.dp))


                Text(
                    "Sucursal Poniente",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )
                Text("Dirección: Pasaje de Pruebas 789, Poniente")
                Text("Teléfono: +56 2 2456 7890")
            }
        }
    }
}