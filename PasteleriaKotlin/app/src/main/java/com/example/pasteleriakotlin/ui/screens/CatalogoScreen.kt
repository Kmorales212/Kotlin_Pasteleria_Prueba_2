package com.example.pasteleriakotlin.ui.screens

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.pasteleriakotlin.datos.DatosEjemplo
import com.example.pasteleriakotlin.datos.Producto
import com.example.pasteleriakotlin.navegacion.RUTA_CATALOGO
import com.example.pasteleriakotlin.ui.viewModel.CarritoViewModel
import kotlin.text.all
import kotlin.text.format
import kotlin.text.isDigit
import kotlin.text.toIntOrNull

@Composable
fun CatalogoScreen(
    navController: NavController,
    carritoViewModel: CarritoViewModel = viewModel()
) {
    Scaffold(
        topBar = {
            Text(
                "Nuestro Catálogo",
                style = MaterialTheme.typography.headlineLarge,
                modifier = Modifier.padding(16.dp)
            )
        },
        bottomBar = {
            BottomNavigationBar(navController = navController, currentRoute = RUTA_CATALOGO)
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            val listaProductos = DatosEjemplo.catalogoProductos
            items(listaProductos) { producto ->
                ProductoCard(
                    producto = producto,
                    onAgregarClick = { cantidad ->
                        carritoViewModel.agregarAlCarrito(producto, cantidad)
                    }
                )
            }
        }
    }
}


@Composable
fun ProductoCard(
    producto: Producto,
    onAgregarClick: (Int) -> Unit
) {
    var cantidad by remember { mutableStateOf("1") }
    val context = LocalContext.current

    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column {


            Image(
                painter = painterResource(id = producto.imagen),
                contentDescription = producto.nombre,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp),
                contentScale = ContentScale.Crop
            )



            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(producto.nombre, style = MaterialTheme.typography.titleLarge)
                Text(
                    "Precio: $${"%.0f".format(producto.precio)}",
                    style = MaterialTheme.typography.bodyLarge
                )


                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    OutlinedTextField(
                        value = cantidad,
                        onValueChange = {
                            if (it.all { char -> char.isDigit() }) {
                                cantidad = it
                            }
                        },
                        label = { Text("Cant.") },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        modifier = Modifier.width(80.dp)
                    )

                    Spacer(modifier = Modifier.width(16.dp))

                    Button(
                        onClick = {
                            val cantNum = cantidad.toIntOrNull() ?: 0
                            if (cantNum > 0) {
                                onAgregarClick(cantNum)
                                Toast.makeText(
                                    context,
                                    "$cantNum x ${producto.nombre} añadido(s)",
                                    Toast.LENGTH_SHORT
                                ).show()
                                cantidad = "1"
                            } else {
                                Toast.makeText(
                                    context,
                                    "Ingrese una cantidad válida",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        },
                        modifier = Modifier.weight(1f)
                    ) {
                        Text("Añadir")
                    }
                }
            }
        }
    }
}