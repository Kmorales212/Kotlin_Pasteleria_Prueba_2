package com.example.pasteleriakotlin.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.pasteleriakotlin.navegacion.RUTA_HOME
import com.example.pasteleriakotlin.navegacion.RUTA_LOGIN
import com.example.pasteleriakotlin.ui.viewModel.CarritoViewModel

@Composable
fun BoletaScreen(
    navController: NavController,
    carritoViewModel: CarritoViewModel = viewModel()
) {

    val itemsEnCarrito by carritoViewModel.items
    val total by carritoViewModel.total


    LaunchedEffect(itemsEnCarrito) {
        if (itemsEnCarrito.isEmpty()) {
            navController.navigate(RUTA_HOME) {
                popUpTo(RUTA_HOME) { inclusive = true }
            }
        }
    }

    Scaffold(
        topBar = {
            Text(
                "Resumen de Compra",
                style = MaterialTheme.typography.headlineLarge,
                modifier = Modifier.padding(16.dp)
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("¡Gracias por tu compra!", style = MaterialTheme.typography.headlineSmall)
            Spacer(modifier = Modifier.height(16.dp))


            LazyColumn(
                modifier = Modifier.weight(1f), // Ocupa espacio
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                items(itemsEnCarrito) { item ->

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text("${item.cantidad} x ${item.producto.nombre}")
                        Text("$${"%.0f".format(item.producto.precio * item.cantidad)}")
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))


            HorizontalDivider()
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    "TOTAL:",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    "$${"%.0f".format(total)}",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(32.dp))


            Button(
                onClick = {

                    carritoViewModel.limpiarCarrito()


                    navController.navigate(RUTA_HOME) {
                        popUpTo(RUTA_LOGIN) { inclusive = true }
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Finalizar y Volver al Inicio")
            }
        }
    }
}