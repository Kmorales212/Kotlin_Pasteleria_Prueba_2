package com.example.pasteleriakotlin.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.pasteleriakotlin.navegacion.RUTA_CARRITO
import com.example.pasteleriakotlin.navegacion.RUTA_CATALOGO
import com.example.pasteleriakotlin.navegacion.RUTA_CONTACTO
import com.example.pasteleriakotlin.navegacion.RUTA_HOME

@Composable
fun HomeScreen(navController: NavController) {


    Scaffold(
        bottomBar = {

            BottomNavigationBar(navController = navController, currentRoute = RUTA_HOME)
        }
    ) { innerPadding ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    "¡Bienvenidos a Pastelería Kotlin!",
                    style = androidx.compose.material3.MaterialTheme.typography.headlineMedium
                )
                Text("Somos una pastelería dedicada a crear los momentos más dulces.")
            }
        }
    }
}


@Composable
fun BottomNavigationBar(navController: NavController, currentRoute: String) {
    NavigationBar {

        NavigationBarItem(
            icon = { Icon(Icons.Filled.Home, contentDescription = "Inicio") },
            label = { Text("Quiénes Somos") },
            selected = (currentRoute == RUTA_HOME), // Se marca si estamos en esta ruta
            onClick = {
                if (currentRoute != RUTA_HOME) {
                    navController.navigate(RUTA_HOME) {

                        popUpTo(navController.graph.startDestinationId)
                        launchSingleTop = true
                    }
                }
            }
        )


        NavigationBarItem(
            icon = { Icon(Icons.Filled.Star, contentDescription = "Catálogo") },
            label = { Text("Catálogo") },
            selected = (currentRoute == RUTA_CATALOGO),
            onClick = {
                if (currentRoute != RUTA_CATALOGO) {
                    navController.navigate(RUTA_CATALOGO) {
                        popUpTo(navController.graph.startDestinationId)
                        launchSingleTop = true
                    }
                }
            }
        )


        NavigationBarItem(
            icon = { Icon(Icons.Filled.ShoppingCart, contentDescription = "Carrito") },
            label = { Text("Carrito") },
            selected = (currentRoute == RUTA_CARRITO),
            onClick = {
                if (currentRoute != RUTA_CARRITO) {
                    navController.navigate(RUTA_CARRITO) {
                        popUpTo(navController.graph.startDestinationId)
                        launchSingleTop = true
                    }
                }
            }
        )


        NavigationBarItem(
            icon = { Icon(Icons.Filled.Info, contentDescription = "Contacto") },
            label = { Text("Contacto") },
            selected = (currentRoute == RUTA_CONTACTO),
            onClick = {
                if (currentRoute != RUTA_CONTACTO) {
                    navController.navigate(RUTA_CONTACTO) {
                        popUpTo(navController.graph.startDestinationId)
                        launchSingleTop = true
                    }
                }
            }
        )
    }
}