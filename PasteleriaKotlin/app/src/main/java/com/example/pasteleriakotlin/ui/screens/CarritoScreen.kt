package com.example.pasteleriakotlin.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.pasteleriakotlin.datos.ItemCarrito
import com.example.pasteleriakotlin.navegacion.RUTA_BOLETA
import com.example.pasteleriakotlin.navegacion.RUTA_CARRITO
import com.example.pasteleriakotlin.ui.viewModel.CarritoViewModel
import kotlin.text.format

@Composable
fun CarritoScreen(
    navController: NavController,
    carritoViewModel: CarritoViewModel = viewModel()
) {

    val itemsEnCarrito by carritoViewModel.items
    val total by carritoViewModel.total

    Scaffold(
        topBar = {
            Text(
                "Mi Carrito",
                style = MaterialTheme.typography.headlineLarge,
                modifier = Modifier.padding(16.dp)
            )
        },
        bottomBar = {

            BottomNavigationBar(navController = navController, currentRoute = RUTA_CARRITO)
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp)
        ) {

            if (itemsEnCarrito.isEmpty()) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text("Tu carrito está vacío")
                }
            } else {

                LazyColumn(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(itemsEnCarrito) { item ->
                        ItemCarritoCard(
                            item = item,
                            onEliminarClick = {

                                carritoViewModel.eliminarDelCarrito(item)
                            }
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))


                HorizontalDivider()
                Spacer(modifier = Modifier.height(16.dp))


                Text(
                    "Total: $${"%.0f".format(total)}",
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = {

                        navController.navigate(RUTA_BOLETA)
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Pagar")
                }
            }
        }
    }
}


@Composable
fun ItemCarritoCard(item: ItemCarrito, onEliminarClick: () -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(modifier = Modifier.weight(1f)) { // Columna para texto
                Text(
                    item.producto.nombre,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
                Text("Cantidad: ${item.cantidad}", style = MaterialTheme.typography.bodyMedium)
                Text(
                    "Subtotal: $${"%.0f".format(item.producto.precio * item.cantidad)}",
                    style = MaterialTheme.typography.bodyMedium
                )
            }


            IconButton(onClick = onEliminarClick) {
                Icon(
                    Icons.Filled.Delete,
                    contentDescription = "Eliminar",
                    tint = MaterialTheme.colorScheme.error
                )
            }
        }
    }
}